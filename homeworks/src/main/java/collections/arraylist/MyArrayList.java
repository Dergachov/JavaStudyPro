package collections.arraylist;

import java.util.Iterator;

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
        if (arraySize == 0)
            throw new IndexOutOfBoundsException("MyArrayList(int arraySize) --> Array size can`t be null");
        this.data = new Object[arraySize];
        this.size = data.length;
        this.cursor = 0;
    }

    public void add(T value) {
        if (cursor == size) {
            Object[] temp = new Object[(int) ((size + capacity) * 1.5)];
            System.arraycopy(data, 0, temp, 0, data.length);
            data = temp;
            data[cursor] = value;
            size = data.length;
            ++cursor;
        } else {
            data[cursor] = value;
            ++cursor;
        }
    }

    public void add(int index, T value) {
        if (index == cursor && cursor != 0)
            throw new IndexOutOfBoundsException("addData(int index, T value) --> Index out of array range");
        if (index == 0) { //BEGIN
            Object[] temp = new Object[size + 1];
            temp[0] = value;
            System.arraycopy(data, 0, temp, 1, size - (size - cursor));
            data = temp;
            size = data.length;
            ++cursor;
        } else if (index == cursor - 1) { // END
            if ((size - cursor) <= 0) { // if doesn't free cells
                Object[] temp = new Object[size + capacity];
                System.arraycopy(data, 0, temp, 0, cursor);
                temp[cursor] = value;
                data = temp;
                ++cursor;
            } else {
                data[cursor] = value;
                cursor++;
            }
        } else {
            Object[] temp = new Object[size + 1];
            System.arraycopy(data, 0, temp, 0, index);
            temp[index] = value;
            System.arraycopy(data, index, temp, index + 1, size - index);
            data = temp;
            ++cursor;
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

    public int getLength() {
        return size;
    }

    public int getSize() {
        return cursor - 1;
    }

    public boolean contains(Object o) {
        for (int i = 0; i < cursor; i++) {
            if (get(i).equals(o)) return true;
        }
        return false;
    }

    //debug
    public int getCursor() {
        return cursor;
    }

    public void remove(int index) {
        if (index == cursor && cursor != 0)
            throw new IndexOutOfBoundsException("getData(int index) --> Index out of array range");
        if (index == 0 && cursor > 1) {// If 'delete position' will be in BEGIN of array
            if ((size - cursor) > capacity) { //Optimization for method add();
                Object[] temp = new Object[(size - (size - cursor)) + capacity];
                System.arraycopy(data, 1, temp, 0, size - (size - cursor));
                data = temp;
                size = data.length;
            } else {
                Object[] temp = new Object[size];
                System.arraycopy(data, 1, temp, 0, size - 1);
                data = temp;
                size = data.length;
            }
        } else if ((cursor - 1) == index) { // If 'delete position' will be in end of array
            //Optimization
            if (counterFreeCells != capacity) {
                if (size < capacity || size == capacity) { // For small arrays
                    data[index] = null;
                    --cursor;
                    ++counterFreeCells;
                } else //For bigger arrays from high value to default capacity
                {
                    data[index] = null;
                    --cursor;
                    ++counterFreeCells;
                }
            } else {
                Object[] temp = new Object[index];
                System.arraycopy(data, 0, temp, 0, index);
                data = temp;
                size = data.length;
                cursor = index;
                counterFreeCells = 0;
            }
            if (cursor == 1 && index == 0) { // If END now in BEGIN
                Object[] temp = new Object[capacity];
                data = temp;
                size = data.length;
                cursor = 0;
            }
        } else {
            if (size > capacity && capacity < (size - cursor) - 1) { // Delete free cells on the end of arrays
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

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
