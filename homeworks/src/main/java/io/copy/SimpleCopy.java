package io.copy;

import java.io.*;

public class SimpleCopy {
    public static void main(String[] args) {
        File src = new File("src\\main\\java\\io\\copy\\source");
        File dest = new File("src\\main\\java\\io\\copy\\destination");

        try {
            InputStream input = new FileInputStream(src);
            OutputStream output = new FileOutputStream(dest);

            byte[] buf = new byte[4];
            while (input.available() > 0) {
                int len = input.read(buf, 0, buf.length);
                output.write(buf, 0, len);
            }

            input.close();
            output.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
