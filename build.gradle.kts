import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import java.io.FileNotFoundException

plugins {
    kotlin("jvm") version "1.9.21"
    id("com.github.johnrengelman.shadow") version("8.1.1")
    `maven-publish`
}

group = "com.jeff-media"
version = "1.0-SNAPSHOT"

val testServerPath = File("C:\\mctest")

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    //testImplementation("org.jetbrains.kotlin:kotlin-test")

    compileOnly("org.bukkit:bukkit:1.20.5-R0.1-SNAPSHOT-compostchances")

    implementation(kotlin("reflect"))

    // markdown
    implementation("org.commonmark:commonmark:0.21.0")
    implementation("org.commonmark:commonmark-ext-gfm-tables:0.21.0")
}

//tasks.test {
//    useJUnitPlatform()
//}

kotlin {
    jvmToolchain(8)
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

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.jeff-media"
            artifactId = "bukkit-material-list"
            version = project.version.toString()

            from(components["java"])
        }
    }
}