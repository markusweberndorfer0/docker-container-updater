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
    private val sshProperties: SSHProperties
) : SSHService {
    override fun establishSSHConnection(): String {
        val ssh = SSHClient()
        val privateKey = ssh.loadKeys(ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + sshProperties.privateKeyFilePath).path)

        ssh.addHostKeyVerifier(PromiscuousVerifier())
        ssh.connect(sshProperties.host, sshProperties.port)
        ssh.authPublickey(sshProperties.username, privateKey)

        val sshSession = ssh.startSession()

        val cmd = sshSession.exec("ls -la")

        val list: MutableList<String> = mutableListOf()

        val reader = BufferedReader(InputStreamReader(cmd.inputStream))
        var line: String?
        while ((reader.readLine().also { line = it }) != null) {
            line?.let { list.add(it) }
        }

        ssh.close()
        sshSession.close()
        cmd.close()

        return list.joinToString(separator = "\n")
    }
}