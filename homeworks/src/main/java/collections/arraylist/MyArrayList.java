package collections.arraylist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @MyArrayList with default capacity size 12.
 * - void add(T value) if array is full increase increase will by like: (current size + capacity) * 1.5).
 * - field 'cursor' always see in the end of array Object[] data.
 */

public class MyArrayList<T> implements Iterable<T> {
    private int size = 0;
    private int capacity = 12;
    private int cursor;
    private int counterFreeCells = 0;
    private Object[] data;

    public MyArrayList() {
        this.data = new Object[capacity];
        this.size = data.length;
        this.cursor = 0;
    }

    public MyArrayList(int arraySize) {
        if (arraySize == 0) {
            this.data = new Object[capacity];
            this.size = data.length;
            this.cursor = 0;
        } else {
            this.data = new Object[arraySize];
            this.size = data.length;
            this.cursor = 0;
        }
    }

    /**
     * @Method add() default add item in the end of array Object[] data
     * and will by increase if no free cells available like (size + capacity) * 1.5)
     */
    public void add(T value) {
        //resize here
        if (cursor == size) {
            Object[] temp = new Object[(int) ((size + capacity) * 1.5)];
            System.arraycopy(data, 0, temp, 0, data.length);
            data = temp;
            data[cursor] = value;
            size = data.length;
            ++cursor;
        } else { //default
            data[cursor] = value;
            ++cursor;
        }
    }

    public void add(int index, T value) {
        if (index == cursor && cursor != 0)
            throw new IndexOutOfBoundsException("addData(int index, T value) --> Index out of array range");
        /**
         * for insert in begin of array Object[] data
         */
        if (index == 0) {
            Object[] temp = new Object[size + 1];
            temp[0] = value;
            System.arraycopy(data, 0, temp, 1, size - (size - cursor));
            data = temp;
            size = data.length;
            ++cursor;
            /**
             * for insert in the end of array Object[] data
             */
        } else if (index == cursor - 1) {
            /**
             * check for free space in array Object[] data
             */
            if ((size - cursor) <= 0) {
                Object[] temp = new Object[size + capacity];
                System.arraycopy(data, 0, temp, 0, cursor);
                temp[cursor] = value;
                data = temp;
                ++cursor;
            } else {
                data[cursor] = value;
                ++cursor;
            }
        } else {
            /**
             * for insert in the middle of array Object[] data
             */
            Object[] temp = new Object[size + 1];
            System.arraycopy(data, 0, temp, 0, index);
            temp[index] = value;
            System.arraycopy(data, index, temp, index + 1, size - index);
            data = temp;
            ++cursor;
        }
    }

    public void remove(int index) {
        if (index == cursor && cursor != 0)
            throw new IndexOutOfBoundsException("getData(int index) --> Index out of array range");
        /**
         * for delete in the begin of array Object[] data
         */
        if (index == 0 && cursor > 1) {
            /**
             * there is optimization for method like add();
             */
            if ((size - cursor) > capacity) { //check current capacity
                Object[] temp = new Object[(size - (size - cursor)) + capacity];
                System.arraycopy(data, 1, temp, 0, size - (size - cursor));
                data = temp;
                size = data.length;
                --cursor;
            } else {
                Object[] temp = new Object[size];
                System.arraycopy(data, 1, temp, 0, size - 1);
                data = temp;
                size = data.length;
                --cursor;
            }
            /**
             *for delete in the end of array Object[] data
             */
        } else if ((cursor - 1) == index) {
            /**
             * there is optimization for resize of array Object[] data
             */
            if (counterFreeCells != capacity) {
                data[index] = null;
                --cursor;
                ++counterFreeCells;
            } else {
                Object[] temp = new Object[index + capacity];
                System.arraycopy(data, 0, temp, 0, index);
                data = temp;
                size = data.length;
                cursor = index;
                counterFreeCells = 0;
            }
            /**
             * there is optimization for resize of array 'Object[] data' if 'begin == end'
             */
            if (cursor == 1 && index == 0) {
                Object[] temp = new Object[capacity];
                data = temp;
                size = data.length;
                cursor = 0;
            }
        } else {
            /**
             * for delete in the middle of array 'Object[] data' and resize it if have got many free cells
             */
            if (size > capacity && capacity < (size - cursor) - 1) {
                Object[] temp = new Object[(size - (size - cursor)) + capacity];
                System.arraycopy(data, 0, temp, 0, index);
                System.arraycopy(data, index + 1, temp, index, (size - cursor) - 1);
                data = temp;
                size = data.length;
                --cursor;
            } else {
                Object[] temp = new Object[(size - 1)];
                System.arraycopy(data, 0, temp, 0, index);
                System.arraycopy(data, index + 1, temp, index, (size - index) - 1);
                data = temp;
                size = data.length;
                --cursor;
            }
        }
    }

    public T get(int index) {
        if (index == cursor && cursor != 0)
            throw new IndexOutOfBoundsException("getData(int index) --> Index out of array range");
        T item = (T) data[index];
        return item;
    }

    public void set(int index, T value) {
        if (index == cursor && cursor != 0 || index > cursor || cursor < 0)
            throw new IndexOutOfBoundsException("setData(int index, T value) --> Index out of array range");
        data[index] = value;
    }

    /**
     * Method length() return current non free length of array Object[] data
     */
    public int length() {
        return cursor;
    }

    /**
     * Method size() return current size of array Object[] data
     */
    public int size() {
        return size;
    }

    public boolean contains(Object o) {
        for (int i = 0; i < cursor; i++) {
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
            return this.cursor < MyArrayList.this.cursor;
        }

        @Override
        public T next() {
            if (this.hasNext()) {
                ++this.cursor;
                return (T) data[cursor - 1];
            }
            throw new NoSuchElementException();
        }
    }
}
