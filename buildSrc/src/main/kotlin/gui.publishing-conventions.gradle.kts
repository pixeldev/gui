import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.get

plugins {
    id("gui.common-conventions")
    `maven-publish`
}

configure<PublishingExtension> {
    repositories {
        /*maven {
            val snapshot = version.toString().endsWith("-SNAPSHOT")
            val typeName = if (snapshot) "snapshots" else "releases"
            val repoName = "unnamed-$typeName"

            name = repoName
            url = "https://repo.unnamed.team/repository/$repoName"
            credentials {
                username = System.getenv("REPO_USER")
                password = System.getenv("REPO_PASSWORD")
            }
        }*/
    }

    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}