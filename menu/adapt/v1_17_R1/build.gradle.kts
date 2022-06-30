plugins {
    id("gui.publishing-conventions")
}

configure<JavaPluginExtension> {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    api(project(":gui-menu-api"))
    compileOnly("org.spigotmc:spigot:1.17.1-R0.1-SNAPSHOT")
}