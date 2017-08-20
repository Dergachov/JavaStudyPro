package io.byte_array;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class TestMarkSkip {
    public static void main(String[] args) throws IOException {
        byte[] buf = new byte[]{72, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100, 33};
        ByteArrayInputStream in = new ByteArrayInputStream(buf);
        in.skip(10); // skip n bytes
        in.mark(6); // set mark
        int mark = 0;
        while (in.available() > 0) {
            mark++;
            System.out.print((char)in.read());
            if (mark == 5) in.reset(); // reset stream to mark
        }
        in.close();
    }
}
