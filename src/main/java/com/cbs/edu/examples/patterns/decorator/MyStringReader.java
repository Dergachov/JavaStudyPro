package com.cbs.edu.examples.patterns.decorator;

public class MyStringReader extends MyReader {

    private char[] src;
    private int cur;

    public MyStringReader(String src) {
        this.src = src.toCharArray();
    }

    @Override
    char read() {
        return src[cur++];
    }

    @Override
    boolean ready() {
        return cur < src.length;
    }
}
