package eu.weberndorfer.docker_container_updater

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DockerContainerUpdaterApplication

fun main(args: Array<String>) {
	runApplication<DockerContainerUpdaterApplication>(*args)
}
