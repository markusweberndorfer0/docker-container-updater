package eu.weberndorfer.docker_container_updater.api.controller

import eu.weberndorfer.docker_container_updater.api.UpdateApi
import eu.weberndorfer.docker_container_updater.service.UpdateService
import org.springframework.web.bind.annotation.RestController

@RestController
class UpdateController(
    private val updateService: UpdateService
) : UpdateApi {
     override fun getAllUpdates(): Long {
        TODO("Not implemented yet")
    }
}