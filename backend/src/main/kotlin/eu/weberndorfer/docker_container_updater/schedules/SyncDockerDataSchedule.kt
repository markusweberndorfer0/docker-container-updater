package eu.weberndorfer.docker_container_updater.schedules

import org.springframework.scheduling.annotation.Scheduled

class SyncDockerDataSchedule {
    @Scheduled(cron = "0 4 * * *")
    fun compareContainers() {

    }
}