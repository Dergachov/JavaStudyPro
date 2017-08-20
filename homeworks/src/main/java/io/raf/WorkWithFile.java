package io.raf;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile — класс пакета Java IO API, он позволяет перемещаться по файлу,
 * читать из него или писать в него, как вам будет угодно. Вы также сможете заменить
 * существующие части файла, речь идет о обновлении содержимого файла, а точней о обновлении
 * фрагмента файла. Это невозможно сделать с помощью FileInputStream или FileOutputStream,
 * но RandomAccessFile даст вам эту возможность.
 */
public class WorkWithFile {

    private String path;
    private RandomAccessFile file;

    public WorkWithFile(String path) {
        this.path = path;
    }

    // этот метод читает файл и выводит его содержимое
    public String read() throws IOException {
        file = new RandomAccessFile(path, "r");
        String res = "";
        int b = file.read();

        while(b != -1){
            res = res + (char)b;
            b = file.read();
        }
        file.close();

        return res;
    }

    // читаем файл с определенного символа
    public String readFrom(int numberSymbol) throws IOException {
        // открываем файл для чтения
        file = new RandomAccessFile(path, "r");
        String res = "";

        // ставим указатель на нужный вам символ
        file.seek(numberSymbol);
        int b = file.read();

        while(b != -1){
            res = res + (char)b;

            b = file.read();
        }
        file.close();

        return res;
    }

    // запись в файл
    public void write(String st) throws IOException {
        // открываем файл для записи
        // для этого указываем модификатор rw (read & write)
        // что позволит открыть файл и записать его
        file = new RandomAccessFile(path, "rw");

        // записываем строку переведенную в биты
        file.write(st.getBytes());

        // закрываем файл, после чего данные записываемые данные попадут в файл
        file.close();
    }
}
