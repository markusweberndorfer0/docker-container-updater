package eu.weberndorfer.docker_container_updater.service

interface DockerService {
    fun syncContainersWithDatabase(): Boolean
}