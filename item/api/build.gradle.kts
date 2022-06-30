dependencies {
    api(libs.annotations)
    arrayOf("validation", "bukkit").forEach {
        api("team.unnamed:commons-$it:3.1.0")
    }

    compileOnly("org.spigotmc:spigot:1.8.8-R0.1-SNAPSHOT")
}