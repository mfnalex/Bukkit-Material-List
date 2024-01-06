plugins {
    kotlin("jvm") version "1.9.21"
    id("com.github.johnrengelman.shadow") version("8.1.1")
}

group = "com.jeff-media"
version = "1.0-SNAPSHOT"

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