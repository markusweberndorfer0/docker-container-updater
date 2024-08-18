package eu.weberndorfer.docker_container_updater.repository

import eu.weberndorfer.docker_container_updater.entity.Container
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContainerRepository : JpaRepository<Container, Long> {
}