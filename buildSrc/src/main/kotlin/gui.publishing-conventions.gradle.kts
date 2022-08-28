import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.get

plugins {
    id("gui.common-conventions")
    `maven-publish`
}

val snapshotRepository: String by project
val releaseRepository: String by project

configure<PublishingExtension> {
    repositories {
        maven {
            url = if (project.version.toString().endsWith("-SNAPSHOT")) {
                uri(snapshotRepository)
            } else {
                uri(releaseRepository)
            }

            credentials {
                val userKey = "REPO_USER"
                val pwdKey = "REPO_PASSWORD"
                username = project.properties[userKey] as String?
                    ?: System.getenv(userKey)
                password = project.properties[pwdKey] as String?
                    ?: System.getenv(pwdKey)
            }
        }
    }

    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}