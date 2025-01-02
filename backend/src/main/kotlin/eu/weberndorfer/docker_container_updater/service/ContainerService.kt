package eu.weberndorfer.docker_container_updater.service

import eu.weberndorfer.docker_container_updater.api.model.ContainerModel

interface ContainerService {
    fun getAllContainers(): List<ContainerModel>

    fun getContainer(id: Long): ContainerModel?

    fun syncContainers()
}