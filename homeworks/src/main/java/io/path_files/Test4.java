package io.path_files;

import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Eugene on 20.09.2016.
 */
public class Test4 {
    public static void main(String[] args) {
        Path path = Paths.get("C:\\Users\\Eugene\\IdeaProjects\\JavaExamples\\io\\src\\main\\java\\path_files\\Test4.java");

        if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
            System.out.println("The file/directory " + path.getFileName() + " exists");
            if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
                System.out.println(path.getFileName() + " is fs directory");
            } else {
                System.out.println(path.getFileName() + " is fs file");
            }
        } else {
            System.out.println("The file/directory " + path.getFileName() + " does not exist");
        }

        System.out.printf("Readable: %b, Writable: %b, Executable: %b ",
                Files.isReadable(path),
                Files.isWritable(path),
                Files.isExecutable(path)
        );
    }
}
