package eu.weberndorfer.docker_container_updater.service

interface UpdateService {
    fun getAllUpdates(): String
}