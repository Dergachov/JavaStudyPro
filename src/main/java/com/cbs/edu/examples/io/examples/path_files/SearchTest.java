package com.cbs.edu.examples.io.examples.path_files;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.*;

/**
 * Created by Eugene on 20.09.2016.
 */
public class SearchTest extends SimpleFileVisitor<Path> {
    private PathMatcher matcher;

    public SearchTest(String pattern) {
        try {
            matcher = FileSystems.getDefault().getPathMatcher(pattern);
        } catch (IllegalArgumentException iae) {
            System.err.println("Invalid pattern; did you forget to prefix \"glob:\" or \"regex:\"?");
            System.exit(1);
        }

    }

    public FileVisitResult visitFile(Path path, BasicFileAttributes fileAttributes) {
        find(path);
        return FileVisitResult.CONTINUE;
    }

    private void find(Path path) {
        Path name = path.getFileName();
        if (matcher.matches(name))
            System.out.println("Matching file:" + path.getFileName());
    }

    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes fileAttributes) {
        find(path);
        return FileVisitResult.CONTINUE;
    }

    public static void main(String[] args) {
        Path startPath = Paths.get("C:\\Users\\Evgeniy\\Desktop\\JavaStudyPro\\homeworks\\src\\main\\java\\io\\path_files");

        //Строка с glob-шаблоном
        String pattern = "glob:*.java";

        //Строка с regex-шаблоном
        //String pattern = "regex:\\S+\\.java";

        try {
            Files.walkFileTree(startPath, new SearchTest(pattern));
            System.out.println("File search completed!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
