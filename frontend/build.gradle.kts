import com.github.gradle.node.npm.task.NpmTask

plugins {
    id("com.github.node-gradle.node") version "7.0.2"
}

node {
    version = "20.16.0"
    npmVersion = "10.8.2"
    download = true
}

tasks.register<NpmTask>("build") {
    args.set(listOf("run", "build"))

    dependsOn("npm_install")
}