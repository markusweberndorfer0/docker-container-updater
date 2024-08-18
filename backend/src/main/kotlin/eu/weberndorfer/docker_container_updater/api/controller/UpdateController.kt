package eu.weberndorfer.docker_container_updater.api.controller

import eu.weberndorfer.docker_container_updater.api.UpdateApi
import eu.weberndorfer.docker_container_updater.service.DockerService
import eu.weberndorfer.docker_container_updater.service.UpdateService
import org.springframework.web.bind.annotation.RestController

@RestController
class UpdateController(
    private val updateService: UpdateService,
    private val dockerService: DockerService
) : UpdateApi {
    override fun getAllUpdates(): Boolean {
        return dockerService.syncContainersWithDatabase()
    }
}