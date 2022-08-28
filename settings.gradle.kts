rootProject.name = "gui"

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://papermc.io/repo/repository/maven-public/")
    }
}

// item modules
arrayOf("api", "skull-api").forEach {
    includePrefixed("item:$it")
}

// menu modules
arrayOf("api", "plugin").forEach {
    includePrefixed("menu:$it")
}

// menu adapters
arrayOf("1_8_R3", "1_16_R3", "1_17_R1", "1_18_R2", "1_19_R1").forEach {
    includePrefixed("menu:adapt:v$it")
}

fun includePrefixed(name: String) {
    val kebabName = name.replace(':', '-')
    val path = name.replace(':', '/')
    val baseName = "${rootProject.name}-$kebabName"

    include(baseName)
    project(":$baseName").projectDir = file(path)
}