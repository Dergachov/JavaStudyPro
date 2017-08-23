package io;

import java.io.IOException;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.Queue;

public class QueueNIOFilePath {
    public static void main(String[] args) throws IOException {
        String getPath = "./homeworks/src/main/java/io/viewpath/";
        Queue<String> pathsQueue = new LinkedList<>();
        pathsQueue.offer(getPath);

        while (pathsQueue.size() != 0) {
            Path path = Paths.get(pathsQueue.poll());
            if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
                DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
                for (Path paths : directoryStream) {
                    if (Files.isDirectory(paths, LinkOption.NOFOLLOW_LINKS)) {
                        pathsQueue.offer(paths.toString());
                        System.out.println("Directory: " + paths.getFileName().toString());
                    } else {
                        System.out.println("File: " + paths.getFileName().toString());
                    }
                }
            } else {
                System.out.println("Not exists: " + path.toString());
            }
        }
    }
}