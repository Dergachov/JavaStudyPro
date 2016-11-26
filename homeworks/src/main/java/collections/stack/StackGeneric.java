package collections.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.lang.System.exit;

/**
 * Created by Serezha on 2016-11-22.
 *
 * @StackGeneric used LIFO principle
 * Stack using AutoResize if variable countUnusedArrayCells >= 20 empty cells.
 * If set size in constructor then the stack will NOT decrease less set size
 * Defined in private method changeArraySize();
 */
public class StackGeneric<T> implements Iterable<T> {

    private Object[] data;
    private int cursor = -1;
    private int setSize = 0;
    /**
     * Field '<p>countUnusedArrayCells<p/>' for count empty cells in array '<p>Object[] data<p/>'.
     */
    private int countUnusedArrayCells = 0;

    public StackGeneric() {
    }

    public StackGeneric(int size) {
        this.data = new Object[size];
        this.setSize = size;
    }

    public int getStackSize() {
        return cursor;
    }

    public void push(T item) {
        /**
         * @if This is using if run empty default constructor
         */
        if (cursor < 0 && setSize == 0) {
            data = new Object[1];
            ++cursor;
            /**
             * @elseif This is using if run constructor with static size
             */
        } else if (cursor < 0 && setSize > 0) ++cursor;
        /**
         * @changeArraySize(1) Option 1 - Check current size of Stack and if it is necessary to expand
         */
        changeArraySize(1);
        data[cursor++] = item;
    }

    public T pop() {
        try {
            T item = (T) data[cursor - 1];
            data[cursor - 1] = null;
            --cursor;
            ++countUnusedArrayCells;
            changeArraySize(2);
            return item;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error! Your stack is empty. Can`t call method pop(); Size stack now is == " + getStackSize() + "\nException: " + e);
            exit(1);
            return null;
        }
    }

    /**
     * @changeArraySize() this method for resize Stack
     * option 1 = expand size of Stack
     * option 2 = narrows size of Stack if variable 'countUnusedArrayCells' >= 20 empty cells.
     */
    private void changeArraySize(int option) {
        switch (option) {
            case 1:
                if (data.length == cursor) {
                    Object[] temp = new Object[cursor + 10];
                    System.arraycopy(data, 0, temp, 0, data.length);
                    data = temp;
                }
                break;
            /**
             *@if (left if used default constructor  |  right if used constructor with static size)
             */
            case 2:
                if ((countUnusedArrayCells >= 20 && setSize == 0) | (countUnusedArrayCells >= 20 && setSize <= cursor)) {
                    Object[] temp = new Object[cursor];
                    System.arraycopy(data, 0, temp, 0, cursor);
                    data = temp;
                    countUnusedArrayCells = 0;
                }
                break;
            default:
                System.out.println("Error in private method changeArraySize(); Bad option!");
                exit(1);
                break;
        }
    }

    // Iterator @Override
    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    // Inner class implements Iterator
    private class StackIterator implements Iterator<T> {
        private int cursor;

        public StackIterator() {
            this.cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return this.cursor < StackGeneric.this.cursor;
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

