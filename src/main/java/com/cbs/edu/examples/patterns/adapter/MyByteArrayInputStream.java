package com.cbs.edu.examples.patterns.adapter;

public class MyByteArrayInputStream extends MyInputStream {

    private byte[] arr;
    private int cur = 0;

    public MyByteArrayInputStream(byte[] arr) {
        this.arr = arr;
    }

    @Override
    int read() {
        return arr[cur++];
    }

    @Override
    int available() {
        return cur < arr.length ? 1 : -1;
    }
}
