rootProject.name = "gui"

arrayOf("api").forEach {
    includePrefixed("item:$it")
}

arrayOf("api").forEach {
    includePrefixed("menu:$it")
}

fun includePrefixed(name: String) {
    val kebabName = name.replace(':', '-')
    val path = name.replace(':', '/')
    val baseName = "${rootProject.name}-$kebabName"

    include(baseName)
    project(":$baseName").projectDir = file(path)
}