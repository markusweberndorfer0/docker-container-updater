package eu.weberndorfer.docker_container_updater.repository

import eu.weberndorfer.docker_container_updater.entity.Container
import org.springframework.data.jpa.repository.JpaRepository

interface ContainerRepository : JpaRepository<Container, Long> {
}