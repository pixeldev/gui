plugins {
    `maven-publish`
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")

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

    repositories {
        mavenLocal()
        maven("https://repo.unnamed.team/repository/unnamed-public/")
        maven("https://repo.codemc.io/repository/nms/")
        mavenCentral()
    }
}
