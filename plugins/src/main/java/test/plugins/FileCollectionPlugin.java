package test.plugins;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class FileCollectionPlugin implements Plugin<Project> {
    @Override
    public void apply(Project target) {
        System.out.println("applying plugin");
    }
}
