package eu.weberndorfer.docker_container_updater.api

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping

@Tag(name = "Update")
interface UpdateApi : BaseApi {
    @GetMapping("/updates")
    fun getAllUpdates(): Boolean
}