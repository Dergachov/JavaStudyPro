package io.file;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * File is an abstract representation of file or directory.
 */
public class Test {
    public static void main(String[] args) {
        try {
            File file = new File("C:\\Users\\Evgeniy\\Desktop\\java-professional-04\\src\\main\\java\\com\\cbs\\edu\\examples\\io\\file\\file_data.txt");

            System.out.println("Name of the file or directory: " + file.getName());
            System.out.println("File/directory path: " + file.getPath());
            System.out.println("Pathname string of abstract pathname's parent: " + file.getParent());
            System.out.println("Absolute path: " + file.getAbsolutePath());
            System.out.println("Can read?: " + file.canRead());
            System.out.println("Can write?: " + file.canWrite());
            System.out.println("Is file?: " + file.isFile());
            System.out.println("Is directory?: " + file.isDirectory());

            // Create new file/directory if it not exist
            if (!file.exists()) {
                boolean isCreated = file.createNewFile();
                System.out.println("New file created: " + isCreated);
            }

            Date date = new Date(file.lastModified());
            System.out.println("Last modified: " + date);

            System.out.println("File size(in bytes): " + file.length());

//            boolean isRenamed = file.renameTo(new File("C:\\Users\\Eugene\\IdeaProjects\\JavaExamples\\io\\src\\main\\java\\file\\file_data.txt"));
//            System.out.println("File renamed: " + isRenamed);

            File newDirectory = new File("C:\\Users\\Evgeniy\\Desktop\\java-professional-02\\src\\main\\java\\com\\cbs\\edu\\io\\file\\new_dir");
            System.out.println("Create new directory: " + newDirectory.mkdir());

//            file.renameTo(new File("C:\\Users\\Eugene\\IdeaProjects\\JavaExamples\\io\\src\\main\\java\\file\\temp", file.getName()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
