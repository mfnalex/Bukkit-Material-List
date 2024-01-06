import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import java.io.FileNotFoundException

plugins {
    kotlin("jvm") version "1.9.21"
    id("com.github.johnrengelman.shadow") version("8.1.1")
}

group = "com.jeff-media"
version = "1.0-SNAPSHOT"

val testServerPath = File("C:\\mctest")

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    //testImplementation("org.jetbrains.kotlin:kotlin-test")
    compileOnly("org.spigotmc:spigot-api:1.20.2-R0.1-SNAPSHOT")
    implementation(kotlin("reflect"))
}

//tasks.test {
//    useJUnitPlatform()
//}

kotlin {
    jvmToolchain(17)
}

tasks.processResources {
    filesMatching("plugin.yml") {
        expand(project.properties)
    }
}

tasks.build {
    dependsOn("shadowJar")
}

tasks.register<Copy>("copyToTestServer") {
    if(testServerPath.isDirectory) {
        from(tasks.getByName<ShadowJar>("shadowJar").archiveFile)
        destinationDir = File(testServerPath, "plugins")
    } else {
        throw FileNotFoundException("Test server path not found: ${testServerPath.absolutePath}")
    }
}

if(testServerPath.exists()) {
    tasks.getByName("build").dependsOn("copyToTestServer")
}