dependencies {
    api(project(":abstraction"))

    arrayOf(
        "v1_7_R4", "v1_8_R3", "v1_9_R2",
        "v1_10_R1", "v1_11_R1", "v1_12_R1",
        "v1_13_R2", "v1_14_R1", "v1_15_R1",
        "v1_16_R1", "v1_16_R3"
    ).forEach {
        runtimeOnly(project(":versions:$it"))
    }

    compileOnly("org.spigotmc:spigot:1.8.8-R0.1-SNAPSHOT")
    api("team.unnamed.common:validation:0.1.1")
}