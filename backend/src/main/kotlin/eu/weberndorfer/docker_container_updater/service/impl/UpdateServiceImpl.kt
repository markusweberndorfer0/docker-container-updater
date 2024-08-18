package eu.weberndorfer.docker_container_updater.service.impl

import eu.weberndorfer.docker_container_updater.repository.ContainerRepository
import eu.weberndorfer.docker_container_updater.service.UpdateService
import org.springframework.stereotype.Service

@Service
class UpdateServiceImpl(
    private var containerRepository: ContainerRepository
) : UpdateService {
    override fun getAllUpdates(): String {
        TODO("Not implemented yet")
    }
}