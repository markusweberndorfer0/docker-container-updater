package eu.weberndorfer.docker_container_updater.api.controller

import eu.weberndorfer.docker_container_updater.api.UpdateApi
import eu.weberndorfer.docker_container_updater.service.DockerService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Update")
@RestController
class UpdateController(
    private val dockerService: DockerService
) : UpdateApi {
    override fun getAllUpdates(): Boolean {
        return dockerService.syncContainersWithDatabase()
    }
}