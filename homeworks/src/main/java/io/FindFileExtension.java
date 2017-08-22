package io;

import java.io.File;

public class FindFileExtension {
    private String path;
    private String fileExtension;

    public FindFileExtension(String path, String extension) {
        this.path = path;
        this.fileExtension = extension.toLowerCase();
    }

    public void FindIO() {
        File file = new File(path);
        File[] fileList = file.listFiles();
        if (fileList == null) {
            return;
        }
        for (File files : fileList) {
            if (files.isFile()) {
                String ext = files.getName();
                ext = ext.substring(ext.lastIndexOf(".") + 1, ext.length());
                if (this.fileExtension.equals(ext)) {
                    System.out.println(files.getName());
                }
            } else {
                this.path = null;
                this.path = files.getAbsolutePath();
                FindIO();
            }
        }
    }

    public static void main(String[] args) {
        FindFileExtension find = new FindFileExtension("./homeworks/src/main/java/io", "java");
        find.FindIO();
    }
}
