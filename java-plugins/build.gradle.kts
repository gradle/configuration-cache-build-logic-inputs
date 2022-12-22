plugins {
    id("java-gradle-plugin")
}

gradlePlugin {
    plugins {
        create("file-collections") {
            id = "test.file-collections"
            implementationClass = "test.plugins.FileCollectionPlugin"
        }
    }
}