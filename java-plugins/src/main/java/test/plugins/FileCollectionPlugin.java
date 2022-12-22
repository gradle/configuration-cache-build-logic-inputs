package test.plugins;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.file.ConfigurableFileTree;

import java.util.List;
import java.util.stream.Collectors;

public class FileCollectionPlugin implements Plugin<Project> {

    private ConfigurableFileTree dir;

    @Override
    public void apply(Project project) {
        project.getTasks().register("queryDirInPlugin", task -> {
            // Query the contents of src/dir eagerly
            dir = project.fileTree("src/dir");
            dir.include("**/*.txt");
            List<String> fileNames = dir.getFiles().stream().map(file -> file.getName()).collect(Collectors.toList());
            task.doLast(t -> {
                System.out.println("files=" + fileNames);
            });
        });
    }
}
