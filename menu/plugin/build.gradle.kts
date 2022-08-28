plugins {
    id("com.github.johnrengelman.shadow") version("7.1.2")
    id("gui.common-conventions")
}

configure<JavaPluginExtension> {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.18.2-R0.1-SNAPSHOT")

    implementation(project(":gui-menu-api"))
    implementation(project(":gui-item-skull-api"))
    implementation(project(":gui-item-api"))

    arrayOf("1_18_R2").forEach {
        runtimeOnly(project(":gui-menu-adapt-v$it"))
    }
}