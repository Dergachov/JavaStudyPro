package io.path_files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 В случае удаления каталога, необходимо, чтобы он был пустым, иначе будет получено исключение (java.nio.file.DirectoryNotEmptyException)
 Если передать в данный метод символьную ссылку, то будет удалена ссылка, а не целевой файл.
 Для данного метода необходимо, чтобы файл существовал, иначе будет получено исключение (java.nio.file.NoSuchFileException).
 Если необходимо игнорировать данные случаи, то лучше подойдёт метод deleteIfExists(),
 который удаляет файл в случае его существования и не бросает подобное исключение при его отсутствии.
 */
public class DeleteTest {
    public static void main(String[] args) {
        Path pathSource = Paths.get("io\\src\\main\\java\\path_files\\DeleteTest.java");
        try {
            Files.delete(pathSource);
            System.out.println("File deleted successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
