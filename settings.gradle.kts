rootProject.name = "gui"

arrayOf("abstraction", "core").forEach {
    includePrefixed(it)
}

arrayOf(
    "v1_7_R4", "v1_8_R3", "v1_9_R2",
    "v1_10_R1", "v1_11_R1", "v1_12_R1",
    "v1_13_R2", "v1_14_R1", "v1_15_R1",
    "v1_16_R1", "v1_16_R3"
).forEach {
    includePrefixed("adapt:$it")
}

fun includePrefixed(name: String) {
    val kebabName = name.replace(':', '-')
    val path = name.replace(':', '/')
    val baseName = "${rootProject.name}-$kebabName"

    include(baseName)
    project(":$baseName").projectDir = file(path)
}