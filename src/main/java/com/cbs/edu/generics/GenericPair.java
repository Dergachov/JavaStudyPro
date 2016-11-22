package com.cbs.edu.generics;

public class GenericPair<T> {
    private T key;
    private T value;

    public GenericPair(T key, T value) {
        this.key = key;
        this.value = value;
    }

    public T getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }

    public static void main(String[] args) {
        GenericPair<Integer> pair1 = new GenericPair<>(10, 20);

        System.out.println(pair1.getKey() + pair1.getValue());
    }
}
