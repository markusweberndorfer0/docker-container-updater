package eu.weberndorfer.docker_container_updater.mapper

import eu.weberndorfer.docker_container_updater.api.model.ContainerModel
import eu.weberndorfer.docker_container_updater.entity.Container

class ContainerMapper {
    fun map(container: Container): ContainerModel {
        return ContainerModel(
            id = container.id,
            name = container.containerName,
            image = container.containerImage,
            updateAvailable = container.updateAvailable,
            localEtag = container.localEtag,
            remoteEtag = container.remoteEtag,
            lastUpdated = container.lastUpdated,
            created = container.created
        )
    }

    fun map(containerModel: ContainerModel): Container {
        return Container(
            id = containerModel.id,
            containerName = containerModel.name,
            containerImage = containerModel.image,
            updateAvailable = containerModel.updateAvailable,
            localEtag = containerModel.localEtag,
            remoteEtag = containerModel.remoteEtag,
            lastUpdated = containerModel.lastUpdated,
            created = containerModel.created
        )
    }
}