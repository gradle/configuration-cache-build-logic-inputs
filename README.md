# configuration-cache-build-logic-inputs

Samples to demonstrate improvements in build logic input tracking. These improvements will be included in Gradle 8.1

## FileCollections queried at configuration time are included in fingerprint

File collections that are queried at configuration time are now treated as configuration inputs and included in
the configuration cache fingerprint. When the contents of these file collections change, the configuration cache
entry is invalidated.

To try this out, use the [`queryDirInDsl`](file-collections/build.gradle.kts) task. The build script queries the contents
of `file-collections/src/dir` at configuration time.

When a new file is added to `file-collections/src/dir`, the configuration cache entry is invalidated.
The console reports that an input of `file-collections/build.gradle.kts` has changed. The message does not yet say exactly
which input has changed. This will be improved in Gradle 8.1 to be more useful.

```shell
> ./gradlew queryDirInDsl

# Cache hit
> ./gradlew queryDirInDsl

# Create a new file
> touch file-collections/src/dir/file2.txt

# Cache miss
> ./gradlew queryDirInDsl
```

Compare this with Gradle 7.6, where the new file is ignored:

```shell
> alias gradle76=# an installation of Gradle 7.6 or a recent Gradle 7.x installation
> gradle76 queryDirInDsl

# Create a new file -> cache hit
> touch file-collections/src/dir/file3.txt

# Incorrect cache hit, teports incorrect file names
> gradle76 queryDirInDsl
```

Here's another example, where a [plugin](java-plugins/src/main/java/test/plugins/FileCollectionPlugin.java) does the same thing:

```shell
> ./gradlew queryDirInPlugin
> ./gradlew queryDirInPlugin

# Change a file -> cache miss
> echo changed > file-collections/src/dir/file3.txt
> ./gradlew queryDirInPlugin

# Create an excluded file -> cache hit
> touch file-collections/src/dir/file3.java
> ./gradlew queryDirInPlugin

# Create an empty directory -> cache hit
> mkdir file-collections/src/dir/empty
> ./gradlew queryDirInPlugin
```

You can try other mutations, such as deleting file or directories, changing their content, etc.
