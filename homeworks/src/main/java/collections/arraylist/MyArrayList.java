package collections.arraylist;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @MyArrayList
 * -
 * -
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

    //READY
    private void init(int incomeSize) {
        if (incomeSize < 0)
            throw new IllegalArgumentException(" Illegal Capacity: " + incomeSize);
        this.data = new Object[(incomeSize > capacity) ? incomeSize : capacity];
        this.length = data.length;
    }

    //READY
    private void checkRange(int incomeVar) {
        if (incomeVar < 0 || incomeVar > size)
            throw new IndexOutOfBoundsException(" Index: " + incomeVar + ", Size: " + size);
    }

    //READY
    private void increaseLength() {
        if (length - size <= 1) {
            temp = Arrays.copyOf(data, data.length);
            data = new Object[(int) (size * 1.005 + capacity)];
            System.arraycopy(temp, 0, data, 0, temp.length);
            length = data.length;
            temp = null;
        }
    }

    //READY
    public boolean add(T value) {
        increaseLength();
        data[size++] = value;
        return true;
    }

    //READY
    public boolean add(int index, T value) {
        checkRange(index);
        if (index <= size - 1) {// check this
            increaseLength();
            temp = new Object[size - index];
            System.arraycopy(data, index, temp, 0, size - index);
            data[index] = value;
            System.arraycopy(temp, 0, data, index + 1, size - index);
            ++size;
            temp = null;
            return true;
        }
        return (add(value)) ? true : false;
    }
    //CODING
    public void remove(int index) {
        checkRange(index);
    }

    //READY
    public T get(int index) {
        T item = (T) data[index];
        return item;
    }

    //READY
    public void set(int index, T value) {
        checkRange(index);
        data[index] = value;
    }

    public int size() {
        return this.size;
    }

    public boolean contains(Object o) {
        for (int i = 0; i < this.size; i++) {
            if (get(i).equals(o)) return true;
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