# configuration-cache-build-logic-inputs

Samples to demonstrate improvements in build logic input tracking

## FileCollections queried at configuration time are included in fingerprint

```shell
> ./gradlew queryDirInDsl
> touch file-collections/src/dir/file2.txt
> ./gradlew queryDirInDsl
```

```shell
> ./gradlew queryDirInPlugin
> touch file-collections/src/dir/file3.txt
> ./gradlew queryDirInPlugin
```
