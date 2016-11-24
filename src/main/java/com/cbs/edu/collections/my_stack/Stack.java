package com.cbs.edu.collections.my_stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<T> implements Iterable<T> {

    private Object[] data;
    private int cursor = -1;
    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;

    public Stack() {
        data = new Object[DEFAULT_CAPACITY];
    }

    public Stack(int capacity) {
        data = new Object[capacity];
    }

    public int getSize() {
        return size;
    }

    public void push(T elem) {
        if (size == data.length) {
            Object[] temp = new Object[size * 2];
            System.arraycopy(data, 0, temp, 0, data.length);
            data = temp;
//            temp = null;
        }
        data[++cursor] = elem;
        size++;
    }

    public T pop() {
        T elem = (T) data[cursor];
        data[cursor--] = null;
        size--;
        return elem;
//        return (T) data[cursor--]; memory leak
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {

        @Override
        public boolean hasNext() {
            return cursor >= 0;
        }

        @Override
        public T next() {
            if (hasNext()) {
                return (T)data[cursor--];
            }
            throw new NoSuchElementException();        }
    }

    public static void main(String[] args) {
        Stack<String> stringStack = new Stack<>(10);

        for (int i = 0; i < 20; i++) {
            stringStack.push("Element #" + i);
            System.out.println("Push Element #" + i);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("Pop " + stringStack.pop());
        }

        Iterator<String> iterator = stringStack.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
