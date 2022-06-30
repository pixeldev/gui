import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion

plugins {
    `java-library`
}

repositories {
    mavenLocal()
    maven("https://repo.unnamed.team/repository/unnamed-public/")
    maven("https://repo.codemc.io/repository/nms/")
    mavenCentral()
}

configure<JavaPluginExtension> {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

tasks {
    compileJava {
        options.compilerArgs.add("-parameters")
    }
}