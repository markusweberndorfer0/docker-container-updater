package eu.weberndorfer.docker_container_updater.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "ssh")
data class SSHProperties(
    var host: String = "",
    var port: Int = -1,
    var username: String = "",
    var privateKeyFilePath: String = "",
)