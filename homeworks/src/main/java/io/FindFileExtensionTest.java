package io;

public class FindFileExtensionTest {
    public static void main(String[] args) {
        FindFileExtension find = new FindFileExtension("./homeworks/src/main/java/io", "txt");

        find.findFileRecursionIO();
        //find.findFileRecursionNIO();

        //find.findFileQueueIO();
        //find.findFileQueueNIO();
    }
}
