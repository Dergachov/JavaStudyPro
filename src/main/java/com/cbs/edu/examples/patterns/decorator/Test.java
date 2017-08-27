package com.cbs.edu.examples.patterns.decorator;

public class Test {
    public static void main(String[] args) {
        MyBufferedReader reader = new MyBufferedReader(new MyStringReader("Hello world!\nHello Earth!"));

        System.out.print(reader.readLine());
        System.out.print(reader.readLine());

    }
}
