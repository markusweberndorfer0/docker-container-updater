package eu.weberndorfer.docker_container_updater.api

import eu.weberndorfer.docker_container_updater.api.model.ContainerModel
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Tag(name="Container")
interface ContainerApi : BaseApi {
    @GetMapping("/containers")
    fun getAllContainers(): List<ContainerModel>

    @GetMapping("/container/{id}")
    fun getContainer(@PathVariable id: Long): ContainerModel?
}