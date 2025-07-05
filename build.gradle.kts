plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.serialization)

    id("io.deepmedia.tools.deployer") version "0.18.0"
}

group = "io.github.0x1bd"
version = "0.1.2"
description = "CSAPI wrapper written in kotlin"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.logback.classic)

    implementation(libs.bundles.ktor.client)

    testImplementation(libs.kotlin.test.junit)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

java {
    withJavadocJar()
    withSourcesJar()
}

deployer {
    content {
        component {
            fromJava()
        }
    }

    projectInfo {
        description = project.description.toString()
        url = "https://github.com/0x1bd/CSAPI-Client"
        scm.fromGithub("0x1bd", "CSAPI-Client")
        license("GNU GPL 3.0", "https://www.gnu.org/licenses/gpl-3.0.txt")
        developer("0x1bd", "0x1bd@proton.me")
        groupId = project.group.toString()
    }

    centralPortalSpec {
        signing.key = secret("SIGNING_KEY")
        signing.password = secret("SIGNING_PASSPHRASE")

        auth.user = secret("CENTRAL_USERNAME")
        auth.password = secret("CENTRAL_PASSWORD")
    }
}