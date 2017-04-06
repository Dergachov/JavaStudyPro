package collections.arraylist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class MyArrayList
 *
 * @version 0.2
 * @since 04.04.2017
 */

public class MyArrayList<T> implements Iterable<T> {
    private int size;
    private int capacity;
    private final int DEFAULT_CAPACITY = 12;
    private Object[] data;
    private Object[] temp;

    public MyArrayList() {
        init(DEFAULT_CAPACITY);
    }

    public MyArrayList(int arraySize) {
        init(arraySize);
    }


    //Method init(int incomeSize) check incoming size and initialization field 'data'.
    private void init(int incomeSize) {
        if (incomeSize < 0)
            throw new IllegalArgumentException(" Illegal Capacity: " + incomeSize);
        this.data = new Object[(incomeSize > DEFAULT_CAPACITY) ? incomeSize : DEFAULT_CAPACITY];
        this.capacity = data.length;
    }

    //Method checkRange(int incomeVar) check incoming index.
    private void checkRange(int incomeVar) {
        if (incomeVar < 0 || incomeVar > size)
            throw new IndexOutOfBoundsException(" Index: " + incomeVar + ", Size: " + size);
    }

    //Method increaseLength() increase array capacity of field 'data'.
    private void increaseLength() {
        if (capacity - size <= 1) {
            temp = data;
            data = new Object[(int) (size * 1.005 + DEFAULT_CAPACITY)];
            System.arraycopy(temp, 0, data, 0, temp.length);
            capacity = data.length;
            temp = null;
        }
    }

    //Method decreaseLength() decrease array capacity of field 'data'.
    private void decreaseLength() {
        if (DEFAULT_CAPACITY * 2 <= capacity - size) {
            temp = data;
            data = new Object[size + DEFAULT_CAPACITY];
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
        return (add(item));
    }

    public T remove(int index) {
        checkRange(index);
        T item = (T) data[index];
        System.arraycopy(data, index + 1, data, index, size - index);
        data[--size] = null;
        decreaseLength();
        return item;
    }

    public T get(int index) {
        return (T) data[index];
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