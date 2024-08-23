package eu.weberndorfer.docker_container_updater.service.impl

import com.fasterxml.jackson.databind.ObjectMapper
import eu.weberndorfer.docker_container_updater.entity.Container
import eu.weberndorfer.docker_container_updater.objects.DockerContainer
import eu.weberndorfer.docker_container_updater.objects.DockerTagResponse
import eu.weberndorfer.docker_container_updater.repository.ContainerRepository
import eu.weberndorfer.docker_container_updater.service.DockerService
import eu.weberndorfer.docker_container_updater.service.SSHService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.client.WebClient
import java.net.URI
import java.time.Instant

@Service
class DockerServiceImpl(
    val sshService: SSHService,
    val objectMapper: ObjectMapper,
    val containerRepository: ContainerRepository
): DockerService {
    private val log = KotlinLogging.logger {  }

    private final val dockerContainersCmd = "docker container ls -a --format '{ \"name\": {{ json .Names }}, \"image\": {{ json .Image }} }'"
    private final val defaultDockerNamespace = "library"
    private final val dockerTagBaseUrl = "https://hub.docker.com"

    @Transactional(readOnly = false)
    override fun syncContainersWithDatabase(): Boolean {
        log.info { "Syncing docker containers between local database and server" }

        sshService.initConnection()

        val dockerContainersJson = sshService.execCommand(dockerContainersCmd)

        val dockerContainers: MutableList<DockerContainer> = mutableListOf()
        dockerContainersJson.split("\n").forEach {
            dockerContainers.add(objectMapper.readValue(it, DockerContainer::class.java))
        }

        var dockerLocalImageJson: String

        val dockerLocalImages: MutableList<String> = mutableListOf()
        dockerContainers.forEach {
            dockerLocalImageJson = sshService.execCommand(getDockerImageCmd(it.image))
            dockerLocalImages.add(objectMapper.readValue(dockerLocalImageJson, Array<String>::class.java)[0]
                .replace(Regex("^.*@"), ""))
        }

        val containers: MutableList<Container> = containerRepository.findAll()

        var localEtag: String
        var remoteEtag: String
        var container: DockerContainer?

        containers.reversed().forEach {
            container = dockerContainers.find { container -> container.name == it.containerName }

            if (container !== null) {
                localEtag = getLocalEtag(it.containerImage)
                remoteEtag = getRemoteEtag(it.containerImage)

                it.localEtag = localEtag
                it.remoteEtag = remoteEtag
                it.updateAvailable = checkIfUpdateAvailable(localEtag, remoteEtag)

                dockerContainers.remove(container)
            } else {
                containers.remove(it)
            }
        }

        dockerContainers.forEach {
            localEtag = getLocalEtag(it.image)
            remoteEtag = getRemoteEtag(it.image)

            containers.add(
                Container(
                    id = -1,
                    containerName = it.name,
                    containerImage = it.image,
                    updateAvailable = checkIfUpdateAvailable(localEtag, remoteEtag),
                    localEtag = localEtag,
                    remoteEtag = remoteEtag,
                    lastUpdated = Instant.now(),
                    created = Instant.now()
                )
            )
        }

        sshService.closeConnection()

        containerRepository.saveAll(containers)

        return true
    }

    private fun checkIfUpdateAvailable(localEtag: String, remoteEtag: String): Boolean {
        return localEtag == remoteEtag
    }

    private fun getLocalEtag(imageName: String): String {
        val dockerLocalImageJson = sshService.execCommand(getDockerImageCmd(imageName))
        return objectMapper.readValue(dockerLocalImageJson, Array<String>::class.java)[0]
            .replace(Regex("^.*@"), "")
    }

    private fun getRemoteEtag(imageName: String): String {
        val imageNameWithoutTag = imageName.replace(Regex(":.*"), "")

        val namespace = Regex(".*(?=/)").find(imageNameWithoutTag)?.value
        val repository = if (namespace.isNullOrBlank()) {
            imageNameWithoutTag
        } else {
            Regex("(?<=/).*").find(imageNameWithoutTag)?.value
        }

        val dockerTagUri = getDockerTagUri(namespace.orEmpty(), repository!!)

        val dockerTagResponse = invokeRemoteTagRequest(dockerTagUri)
            ?: throw IllegalStateException("dockerTagResponse is null")

        return dockerTagResponse.digest
    }

    private fun invokeRemoteTagRequest(uri: URI): DockerTagResponse? {
        val webClient = WebClient.builder()
            .baseUrl(dockerTagBaseUrl)
            .build()

        return webClient.get()
            .uri(uri)
            .retrieve()
            .bodyToMono(DockerTagResponse::class.java)
            .block()
    }

    private fun getDockerTagUri(namespace: String, repository: String): URI {
        return URI.create(dockerTagBaseUrl + "/v2/namespaces/${namespace.ifBlank { defaultDockerNamespace }}/repositories/$repository/tags/latest")
    }

    private fun getDockerImageCmd(imageName: String): String {
        return "docker image inspect $imageName --format '{{ json .RepoDigests }}'"
    }
}