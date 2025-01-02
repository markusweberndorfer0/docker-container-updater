package eu.weberndorfer.docker_container_updater.service.impl

import eu.weberndorfer.docker_container_updater.api.model.ContainerModel
import eu.weberndorfer.docker_container_updater.mapper.ContainerMapper
import eu.weberndorfer.docker_container_updater.repository.ContainerRepository
import eu.weberndorfer.docker_container_updater.service.ContainerService
import eu.weberndorfer.docker_container_updater.service.DockerService
import org.springframework.stereotype.Service

@Service
class ContainerServiceImpl(
    val containerRepository: ContainerRepository,
    val mapper: ContainerMapper,
    val dockerService: DockerService
) : ContainerService {
    override fun getAllContainers(): List<ContainerModel> {
        return containerRepository.findAll().map { mapper.map(it) }
    }

    override fun getContainer(id: Long): ContainerModel? {
        return containerRepository.findById(id).orElseThrow().let { mapper.map(it) }
    }

    override fun syncContainers() {
        dockerService.syncContainersWithDatabase()
    }
}