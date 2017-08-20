package io.path_files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Eugene on 20.09.2016.
 */
public class CopyTest {
    public static void main(String[] args) {
        Path pathSource = Paths.get("io\\src\\main\\java\\path_files\\CopyTest.java");
        Path pathDestination = Paths.get("io\\src\\main\\java\\path_files\\CopyTest.txt");
        try {
            Files.copy(pathSource, pathDestination);
            System.out.println("Source file copied successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
