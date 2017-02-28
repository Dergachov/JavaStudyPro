package collections.arraylist;

/**
 * Created by serezha on 28.02.17.
 */

public class MyArrayList<T> {
    private int size = 0;
    private int capacity = 12;
    private int cursor;
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

    public void addData(T value) {
        if (cursor == size) {
            Object[] temp = new Object[(int) ((size + capacity) * 1.5)];
            System.arraycopy(data, 0, temp, 0, data.length);
            data = temp;
            data[cursor] = value;
            ++cursor;
        } else {
            data[cursor] = value;
            ++cursor;
        }
    }

    public void addData(int index, T value) {
        if (index == cursor)
            throw new IndexOutOfBoundsException("addData(int index, T value) --> Index out of array range");
        data[index] = value;
    }

    public T getData(int index) {
        if (index == cursor) throw new IndexOutOfBoundsException("getData(int index) --> Index out of array range");
        T item = (T) data[index];
        return item;
    }

    public void setData(int index, T value) {
        if (index == cursor)
            throw new IndexOutOfBoundsException("setData(int index, T value) --> Index out of array range");
        data[index] = value;
    }

    public int getSize() {
        return size;
    }

    //debug
    public int getCursor() {
        return cursor;
    }

}
