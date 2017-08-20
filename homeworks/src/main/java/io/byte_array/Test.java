package io.byte_array;

import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException {
        byte[] buf = new byte[]{72, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100, 33};
        String filePath = "src\\main\\java\\io\\byte_array\\file.txt";

        InputStream in = new ByteArrayInputStream(buf);
        OutputStream out = new FileOutputStream(filePath);

        while (in.available() > 0) {
            int aByte = in.read();
            out.write(aByte);
        }

        out.close();
        in.close();
    }
}
