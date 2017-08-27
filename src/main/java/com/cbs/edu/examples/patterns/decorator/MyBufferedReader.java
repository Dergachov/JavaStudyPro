package com.cbs.edu.examples.patterns.decorator;

public class MyBufferedReader extends MyReader {

    private MyReader myReader;

    public MyBufferedReader(MyReader myReader) {
        this.myReader = myReader;
    }

    @Override
    char read() {
        return myReader.read();
    }

    @Override
    boolean ready() {
        return myReader.ready();
    }

    public String readLine() {
        StringBuilder builder = new StringBuilder();
        while (myReader.ready()) {
            char c = myReader.read();
            builder.append(c);

            if (c == '\n' || !myReader.ready()) return builder.toString();
        }
        return null;
    }
}
