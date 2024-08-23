package eu.weberndorfer.docker_container_updater.schedules

import eu.weberndorfer.docker_container_updater.service.DockerService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class SyncDockerDataSchedule(
    private val dockerService: DockerService
) {
    private val log = KotlinLogging.logger {  }

    // Runs every day at 4
    @Scheduled(cron = "0 0 4 * * *")
    fun compareContainers() {
        log.info { "Starting docker sync cronjob" }
        dockerService.syncContainersWithDatabase()
        log.info { "Finished docker sync cronjob" }
    }
}