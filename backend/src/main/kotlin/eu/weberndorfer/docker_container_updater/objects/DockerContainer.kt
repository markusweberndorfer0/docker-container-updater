package eu.weberndorfer.docker_container_updater.objects

data class DockerContainer(
    var name: String,
    var image: String
) {
    override fun toString(): String {
        return "DockerContainer($name, $image)"
    }
}