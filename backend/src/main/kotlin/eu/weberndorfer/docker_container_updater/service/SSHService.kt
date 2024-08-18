package eu.weberndorfer.docker_container_updater.service

interface SSHService {
    fun initConnection()

    fun closeConnection()

    fun execCommand(command: String): String
}