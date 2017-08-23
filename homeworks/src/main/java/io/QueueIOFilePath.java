package io;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class QueueIOFilePath {
    public static void main(String[] args) {
        Queue<String> paths = new LinkedList<>();
        String setPath = "./homeworks/src/main/java/io/viewpath";
        paths.offer(setPath);
        while (paths.size() != 0) {
            File path = new File(paths.poll());
            File[] listFiles = path.listFiles();
            if (listFiles == null) {
                return;
            }
            for (File listing : listFiles) {
                if (listing.isDirectory()) {
                    paths.offer(listing.getPath());
                    System.out.println("./" + listing.getName() + "/");
                } else {
                    System.out.println(listing.getName());
                }
            }
        }
    }
}