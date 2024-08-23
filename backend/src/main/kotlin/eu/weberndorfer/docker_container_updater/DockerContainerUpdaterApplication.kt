package eu.weberndorfer.docker_container_updater

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class DockerContainerUpdaterApplication

fun main(args: Array<String>) {
	runApplication<DockerContainerUpdaterApplication>(*args)
}
