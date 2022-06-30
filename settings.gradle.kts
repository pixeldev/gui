rootProject.name = "gui"

arrayOf("api").forEach {
    includePrefixed("item:$it")
}

arrayOf("api").forEach {
    includePrefixed("menu:$it")
}

arrayOf("1_8_R3", "1_16_R3").forEach {
    includePrefixed("menu:adapt:v$it")
}

fun includePrefixed(name: String) {
    val kebabName = name.replace(':', '-')
    val path = name.replace(':', '/')
    val baseName = "${rootProject.name}-$kebabName"

    include(baseName)
    project(":$baseName").projectDir = file(path)
}