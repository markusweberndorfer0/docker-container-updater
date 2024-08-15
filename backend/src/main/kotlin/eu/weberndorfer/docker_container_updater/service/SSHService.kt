package eu.weberndorfer.docker_container_updater.service

interface SSHService {
    fun establishSSHConnection(): String
}