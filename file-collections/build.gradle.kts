plugins {
    id("test.file-collections")
}

tasks.register("queryDirInDsl") {
    val dir = fileTree("src/dir").matching { include("**/*.txt") }
    val names = dir.map { it.name }
    doLast {
        println("files=$names")
    }
}
