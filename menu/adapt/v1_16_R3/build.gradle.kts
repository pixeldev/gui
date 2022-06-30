plugins {
    id("gui.publishing-conventions")
}

dependencies {
    api(project(":gui-menu-api"))
    compileOnly("org.spigotmc:spigot:1.16.5-R0.1-SNAPSHOT")
}