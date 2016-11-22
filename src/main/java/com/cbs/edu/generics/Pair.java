package com.cbs.edu.generics;

public class Pair {
    private Object key;
    private Object value;

    public Pair(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    public Object getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public static void main(String[] args) {
        Pair pair1 = new Pair(10, 20);
        Pair pair2 = new Pair("Key", "Value");

//        System.out.println(pair2.getKey() + pair2.getValue());
    }
}
