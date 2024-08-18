package eu.weberndorfer.docker_container_updater.entity

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "containers")
class Container(
    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: Long,

    @Column(name = "container_name")
    var containerName: String,

    @Column(name = "container_image")
    var containerImage: String,

    @Column(name = "update_available")
    var updateAvailable: Boolean,

    @Column(name = "local_etag")
    var localEtag: String,

    @Column(name = "remote_etag")
    var remoteEtag: String,

    @Column(name = "last_updated")
    var lastUpdated: Instant,

    @Column(name = "created")
    var created: Instant
)