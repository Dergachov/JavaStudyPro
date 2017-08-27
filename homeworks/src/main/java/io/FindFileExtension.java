package io;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.Queue;

public class FindFileExtension {
    private String path;
    private String fileExtension;
    private Queue<String> pathsQueue;

    public FindFileExtension(String path, String extension) {
        this.path = path;
        this.fileExtension = extension.toLowerCase();
        this.pathsQueue = new LinkedList<>();
    }

    private void extension(String ext) {
        String getName = ext;
        ext = ext.substring(ext.lastIndexOf(".") + 1, ext.length());
        if (this.fileExtension.equals(ext)) {
            System.out.println(getName);
        }
    }


    public void findFileRecursionIO() {
        File file = new File(path);
        File[] fileList = file.listFiles();
        if (fileList == null) {
            return;
        }
        for (File files : fileList) {
            if (files.isFile()) {
                extension(files.getName());
            } else {
                this.path = null;
                this.path = files.getAbsolutePath();
                findFileRecursionIO();
            }
        }
    }

    public void findFileRecursionNIO() {
        Path getPath = Paths.get(path);
        if (Files.exists(getPath, LinkOption.NOFOLLOW_LINKS)) {
            DirectoryStream<Path> directoryStream = null;
            try {
                directoryStream = Files.newDirectoryStream(getPath);
                for (Path paths : directoryStream) {
                    if (Files.isDirectory(paths, LinkOption.NOFOLLOW_LINKS)) {
                        this.path = null;
                        this.path = paths.toString();
                        findFileRecursionNIO();
                    } else {
                        extension(paths.getFileName().toString());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (directoryStream != null) {
                        directoryStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Not exists: " + getPath.toString());
        }
    }

    public void findFileQueueIO() {
        pathsQueue.offer(path);
        while (pathsQueue.size() != 0) {
            File path = new File(pathsQueue.poll());
            File[] listFiles = path.listFiles();
            if (listFiles == null) {
                return;
            }
            for (File listing : listFiles) {
                if (listing.isDirectory()) {
                    pathsQueue.offer(listing.getPath());
                } else {
                    extension(listing.getName());
                }
            }
        }
    }

    public void findFileQueueNIO() {
        pathsQueue.offer(path);
        while (pathsQueue.size() != 0) {
            Path path = Paths.get(pathsQueue.poll());
            if (Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
                DirectoryStream<Path> directoryStream = null;
                try {
                    directoryStream = Files.newDirectoryStream(path);
                    for (Path paths : directoryStream) {
                        if (Files.isDirectory(paths, LinkOption.NOFOLLOW_LINKS)) {
                            pathsQueue.offer(paths.toString());
                        } else {
                            extension(paths.getFileName().toString());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (directoryStream != null) {
                            directoryStream.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("Not exists: " + path.toString());
            }
        }
    }
}
