package io.path_files;

import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Пояснения к коду:
 Метод toUri() возвращает URI (путь который может быть открыт из браузера).
 Метод toAbsolutePath() возвращает абсолютный путь от данного относительного пути. В случае, если был введён абсолютный путь, метод вернёт его же.
 Метод normalize() выполняет нормализацию пути, другими словами удаляет ненужные символы (такие как “ . ” и “ .. ”) из объекта Path.
 Метод toRealPath () возвращает абсолютный путь от полученного пути (как toAbsolutePath ()) и нормализует его (как normalize()).
 Кроме того, если все параметры выбраны правильно, то он может даже работать с символьными ссылками. Однако, для этого метода необходимо,
 чтобы конечный файл/каталог существовал в файловой системе (это не является обязательным условием для других методов Path).
 */
public class Test2 {
    public static void main(String[] args) {
        Path testFilePath = Paths.get(".\\io\\src\\main\\java\\path_files\\file_data.txt");

        System.out.println("The file name is: " + testFilePath.getFileName());
        System.out.println("It's URI is: " + testFilePath.toUri());
        System.out.println("It's absolute path is: "
                + testFilePath.toAbsolutePath());
        System.out.println("It's normalized path is: "
                + testFilePath.normalize());

        //Получение другого объекта строки по нормализованному относительному пути
        Path testPathNormalized = Paths
                .get(testFilePath.normalize().toString());
        System.out.println("It's normalized absolute path is: "
                + testPathNormalized.toAbsolutePath());
        try {
            System.out.println("It's normalized real path is: "
                    + testFilePath.toRealPath(LinkOption.NOFOLLOW_LINKS));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
