package io;

import java.io.File;
import java.io.IOException;

public class IOFilePathRecursion {
    public static void main(String[] args) throws IOException {
        String path = "./homeworks/src/main/java/io/viewpath/";
        FindFiles(path);
    }

    public static void FindFiles(String path) {
        File file = new File(path);
        File[] fileList = file.listFiles();
        if(fileList == null){
            return;
        }
        for (File files : fileList) {
            if (files.isFile()) {
                System.out.println(files.getName());
            } else {
                System.out.println(files.getName());
                FindFiles(files.getPath());
            }
        }
    }
}
