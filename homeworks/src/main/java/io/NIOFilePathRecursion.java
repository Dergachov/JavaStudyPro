package io;


import java.io.IOException;
import java.nio.file.*;

public class NIOFilePathRecursion {
    public static void main(String[] args) throws IOException {
        String path = "./homeworks/src/main/java/io/viewpath/";
        FindFiles(path);
    }

    public static void FindFiles(String getPath) throws IOException {
        Path path = Paths.get(getPath);
        if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
            for (Path paths : directoryStream) {
                if (Files.isDirectory(paths, LinkOption.NOFOLLOW_LINKS)) {
                    System.out.println("Directory: " + paths.getFileName().toString());
                    FindFiles(paths.toString());
                } else {
                    System.out.println("File: " + paths.getFileName().toString());
                }
            }
        } else {
            System.out.println("Not exists: " + path.toString());
        }
    }
}
