package eu.weberndorfer.docker_container_updater.objects

data class DockerTagResponse(
    var creator: Long,
    var id: Int,
    var images: List<Any>,
    var lastUpdated: String?,
    var lastUpdater: Long,
    var lastUpdaterUsername: String?,
    var name: String,
    var repository: Long,
    var fullSize: Long,
    var v2: Boolean,
    var tagStatus: String?,
    var tagLastPulled: String?,
    var tagLastPushed: String?,
    var mediaType: String?,
    var contentType: String?,
    var digest: String,
)
