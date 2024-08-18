package eu.weberndorfer.docker_container_updater.service.impl

import eu.weberndorfer.docker_container_updater.configuration.SSHProperties
import eu.weberndorfer.docker_container_updater.service.SSHService
import net.schmizz.sshj.SSHClient
import net.schmizz.sshj.transport.verification.PromiscuousVerifier
import org.springframework.stereotype.Service
import org.springframework.util.ResourceUtils
import java.io.BufferedReader
import java.io.InputStreamReader


@Service
class SSHServiceImpl(
    private val sshProperties: SSHProperties,
) : SSHService {
    private var sshClient = SSHClient()
    private val privateKeyProvider = sshClient.loadKeys(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + sshProperties.privateKeyFilePath).path)

    override fun initConnection() {
        sshClient = SSHClient()

        sshClient.addHostKeyVerifier(PromiscuousVerifier())
        sshClient.connect(sshProperties.host, sshProperties.port)
        sshClient.authPublickey(sshProperties.username, privateKeyProvider)
    }

    override fun closeConnection() {
        sshClient.disconnect()
        sshClient.close()
    }

    override fun execCommand(command: String): String {
        initConnection()

        val sshSession = sshClient.startSession()
        val commandReturn = sshSession.exec(command)

        val returnLinesList: MutableList<String> = mutableListOf()

        val reader = BufferedReader(InputStreamReader(commandReturn.inputStream))
        var line: String?

        while ((reader.readLine().also { line = it }) != null) {
            line?.let { returnLinesList.add(it) }
        }

        sshSession.close()

        closeConnection()

        return returnLinesList.joinToString(separator = "\n")
    }
}