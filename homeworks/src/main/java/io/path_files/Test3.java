package io.path_files;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Eugene on 20.09.2016.
 */
public class Test3 {
    public static void main(String[] args) {
        Path path1 = Paths.get("SerialazibleTest");
        Path path2 = Paths.get("/home/heorhi/workspace/OCPJP/SerialazibleTest");
        System.out.println("(path1.compareTo(path2) == 0) is: " + (path1.compareTo(path2) == 0));
        System.out.println("path1.equals(path2) is: " + path1.equals(path2));
    }
}
