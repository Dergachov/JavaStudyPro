package com.cbs.edu.examples.patterns.adapter;

public class Test {
    public static void main(String[] args) {
        byte[] buf = new byte[]{72, 101, 108, 108, 111, 32, 119, 111, 114, 108, 100, 33};

        MyInputStream in = new MyByteArrayInputStream(buf);

        while (in.available() > 0) {
            int aByte = in.read();
            System.out.print((char)aByte);
        }
    }
}
