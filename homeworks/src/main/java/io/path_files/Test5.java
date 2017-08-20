package io.path_files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Eugene on 20.09.2016.
 */
public class Test5 {
    public static void main(String[] args) {
        Path path = Paths.get("C:\\Users\\Eugene\\IdeaProjects\\JavaExamples\\io\\src\\main\\java\\path_files\\Test5.java");
        try {
            Object object = Files.getAttribute(path, "creationTime");
            System.out.println("Creation time: " + object);

            object = Files.getAttribute(path, "lastModifiedTime", LinkOption.NOFOLLOW_LINKS);
            System.out.println("Last modified time: " + object);

            object = Files.getAttribute(path, "size");
            System.out.println("Size: " + object);

            object = Files.getAttribute(path, "isDirectory");
            System.out.println("isDirectory: " + object);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
