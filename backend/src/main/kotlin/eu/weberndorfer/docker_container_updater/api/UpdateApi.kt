package eu.weberndorfer.docker_container_updater.api

import org.springframework.web.bind.annotation.GetMapping

interface UpdateApi : BaseApi {
    @GetMapping("/updates")
    fun getAllUpdates(): Boolean
}