plugins {
    id("test.file-collections")
}

tasks.register("queryDirInDsl") {
    // Query the contents of src/dir eagerly
    val dir = fileTree("src/dir").matching { include("**/*.txt") }
    val names = dir.map { it.name }.sorted()
    doLast {
        println("files=$names")
    }
}
