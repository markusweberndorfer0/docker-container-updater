package eu.weberndorfer.docker_container_updater.api.model

import java.time.Instant

class ContainerModel(
    var id: Long,
    var name: String,
    var image: String,
    var updateAvailable: Boolean,
    var localEtag: String,
    var remoteEtag: String,
    var lastUpdated: Instant,
    var created: Instant
) {
    override fun toString(): String {
        return "ContainerModel($id, $name, $image, $updateAvailable, $localEtag, " +
                "$remoteEtag, $lastUpdated, $created"
    }
}