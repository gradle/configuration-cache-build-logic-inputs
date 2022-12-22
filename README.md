# configuration-cache-build-logic-inputs

Samples to demonstrate improvements in build logic input tracking

## FileCollections queried at configuration time are included in fingerprint

Reports that an input has changed. This will later be improved to give the path to the changed file.

```shell
> ./gradlew queryDirInDsl
> ./gradlew queryDirInDsl
> touch file-collections/src/dir/file2.txt
> ./gradlew queryDirInDsl
```

```shell
> ./gradlew queryDirInPlugin
> ./gradlew queryDirInPlugin
> touch file-collections/src/dir/file3.txt
> ./gradlew queryDirInPlugin
> touch file-collections/src/dir/file3.java
> ./gradlew queryDirInPlugin
> mkdir file-collections/src/dir/empty
> ./gradlew queryDirInPlugin
```
