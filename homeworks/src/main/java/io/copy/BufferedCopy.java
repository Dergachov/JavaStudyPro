package io.copy;

import java.io.*;

public class BufferedCopy {
    public static void main(String[] args) {
        File src = new File("src\\main\\java\\io\\copy\\source");
        File dest = new File("src\\main\\java\\io\\copy\\destination");

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(src));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dest));


            for (; bufferedReader.ready(); ) {
                String str = bufferedReader.readLine();
                bufferedWriter.write(str);
            }

            bufferedReader.close();
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
