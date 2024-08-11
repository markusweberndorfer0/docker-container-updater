package eu.weberndorfer.docker_container_updater.repository

import eu.weberndorfer.docker_container_updater.entity.Update
import org.springframework.data.jpa.repository.JpaRepository

interface UpdateRepository : JpaRepository<Update, Long> {
}