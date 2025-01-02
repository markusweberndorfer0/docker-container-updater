package eu.weberndorfer.docker_container_updater.api.controller

import eu.weberndorfer.docker_container_updater.api.ContainerApi
import eu.weberndorfer.docker_container_updater.api.model.ContainerModel
import eu.weberndorfer.docker_container_updater.service.ContainerService
import org.springframework.web.bind.annotation.RestController

@RestController
class ContainerController(val containerService: ContainerService) : ContainerApi {
    override fun getAllContainers(): List<ContainerModel> {
        return containerService.getAllContainers()
    }

    override fun getContainer(id: Long): ContainerModel? {
        return containerService.getContainer(id)
    }

    override fun syncContainers() {
        return containerService.syncContainers()
    }
}