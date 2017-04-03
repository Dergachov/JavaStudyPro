package collections.arraylist;

//import java.util.Arrays;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @MyArrayList
 */

public class MyArrayList<T> implements Iterable<T> {
    private int size;
    private int length;
    private final int capacity = 12;
    private Object[] data;
    private Object[] temp;

    public MyArrayList() {
        init(capacity);
    }

    public MyArrayList(int arraySize) {
        init(arraySize);
    }


    private void init(int incomeSize) {
        if (incomeSize < 0)
            throw new IllegalArgumentException(" Illegal Capacity: " + incomeSize);
        this.data = new Object[(incomeSize > capacity) ? incomeSize : capacity];
        this.length = data.length;
    }

    private void checkRange(int incomeVar) {
        if (incomeVar < 0 || incomeVar > size)
            throw new IndexOutOfBoundsException(" Index: " + incomeVar + ", Size: " + size);
    }

    private void checkRemoveRange(int incomeVar) {
        if (incomeVar < 0 || incomeVar >= size)
            throw new IndexOutOfBoundsException(" Index: " + incomeVar + ", Size: " + size);
    }

    private void increaseLength() {
        if (length - size <= 1) {
            //temp = Arrays.copyOf(data, data.length); // It's good or 'temp = data; or System.arraycopy' ?
            temp = data;
            data = new Object[(int) (size * 1.005 + capacity)];
            System.arraycopy(temp, 0, data, 0, temp.length);
            length = data.length;
            temp = null;
        }
    }

    private void decreaseLength() {
        if (capacity * 2 <= length - size) {
            temp = data;
            data = new Object[size + capacity];
            System.arraycopy(temp, 0, data, 0, size);
            temp = null;
            System.gc();
        }
    }

    public boolean add(T value) {
        increaseLength();
        data[size++] = value;
        return true;
    }

    public boolean add(int index, T item) {
        checkRange(index);
        if (index <= size - 1) {
            increaseLength();
            temp = new Object[size - index];
            System.arraycopy(data, index, temp, 0, size - index);
            data[index] = item;
            System.arraycopy(temp, 0, data, index + 1, size - index);
            ++size;
            temp = null;
            return true;
        }
        return (add(item)) ? true : false;
    }

    public T remove(int index) {
        checkRemoveRange(index);
        T item = (T) data[index];
        System.arraycopy(data, index + 1, data, index, size - index);
        data[--size] = null; // it makes sense of ' = null' ?
        decreaseLength();
        return item;
    }


    public T get(int index) {
        T item = (T) data[index];
        return item;
    }

    public boolean set(int index, T item) {
        checkRange(index);
        data[index] = item;
        return true;
    }

    public int size() {
        return size;
    }

    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (get(i).equals(o)) return true; // Maybe () ? :
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }

    private class MyArrayListIterator implements Iterator<T> {
        private int cursor;

        public MyArrayListIterator() {
            this.cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return this.cursor < MyArrayList.this.size;
        }

        @Override
        public T next() {
            if (this.hasNext()) return (T) data[cursor++];
            throw new NoSuchElementException();
        }
    }
}