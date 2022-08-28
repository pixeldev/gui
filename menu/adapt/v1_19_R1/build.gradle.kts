plugins {
    id("gui.publishing-conventions")
    id("io.papermc.paperweight.userdev") version "1.3.5"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    maven("https://libraries.minecraft.net/")
}

tasks {
    assemble {
        dependsOn(reobfJar)
    }
}

dependencies {
    api(project(":gui-menu-api"))
    paperDevBundle("1.19.2-R0.1-SNAPSHOT")
}