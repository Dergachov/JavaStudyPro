package hw01.collections.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.lang.System.exit;

/**
 * Created by Serezha on 2016-11-22.
 *
 * @StackGeneric used LIFO principle
 * <p/>
 * Field Array<T> 'sArray' is main array in class StackGeneric.
 * Field Array<T> 'tArray' is temporary array in class StackGeneric.
 * Field 'countUnusedArrayCells' for count empty cells in array 'sArray'.
 * <p/>
 * Resize Array<T> 'sArray' will be run if variable countUnusedArrayCells >= 20 empty cells.
 * If set size in constructor then the array will NOT decrease less set size
 * Defined in private method changeArraySize();
 */
public class StackGeneric<T> implements Iterable<T> {

    private T[] sArray;
    private T[] tArray;
    private int size = -1; // TODO: 2016-11-22 How to make with a long type?
    private int setSize = 0;
    private int countUnusedArrayCells = 0;

    public StackGeneric() {
    }

    public StackGeneric(int size) {
        // Cast Object for <T> type
        this.sArray = (T[]) new Object[size];
        this.setSize = size;
    }

    public int getSize() {
        return size;
    }

    // for debug, delete later..
    public int getArrSize() {
        return sArray.length;
    }

    // for debug, delete later..
    public int getCountUnusedArrayCells() {
        return countUnusedArrayCells;
    }

    public void push(T item) {
        //This is using if run empty default constructor
        if (size < 0 && setSize == 0) {
            sArray = (T[]) new Object[1];
            ++size;
            //This is using if run constructor with size
        } else if (size < 0 && setSize > 0) ++size;

        // checking current size array 'sArray'
        changeArraySize(1);
        sArray[size++] = item;
    }

    public T pop() {
        try {
            T item;
            item = sArray[size - 1];
            sArray[size - 1] = null;
            --size;
            ++countUnusedArrayCells;
            changeArraySize(2);
            return item;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error! Your stack is empty. Can`t call method pop(); Size stack now is == " + getSize() + "\nException: " + e);
            exit(1);
            return null;
        }
    }

    /**
     * @changeArraySize() this method for change array size 'sArray'
     * option 1 = expand array size 'sArray'
     * option 2 = narrows array size 'sArray' if variable 'countUnusedArrayCells' >= 20 empty cells.
     */
    private void changeArraySize(int option) {
        switch (option) {
            case 1:
                if (sArray.length == size) {
                    tArray = (T[]) new Object[sArray.length];
                    tArray = sArray.clone();
                    sArray = null;
                    sArray = (T[]) new Object[tArray.length + 1];
                    for (int i = 0; i < tArray.length; i++) {
                        sArray[i] = tArray[i];
                    }
                    tArray = null;
                }
                break;
            case 2: // left if used default constructor  |  right if used constructor with size
                if ((countUnusedArrayCells >= 20 && setSize == 0) | (countUnusedArrayCells >= 20 && setSize <= size)) {
                    tArray = (T[]) new Object[size];
                    tArray = sArray.clone();
                    sArray = null;
                    sArray = (T[]) new Object[size];
                    for (int i = 0; i < size; i++) {
                        sArray[i] = tArray[i];
                    }
                    tArray = null;
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
        return new StackIterator(sArray);
    }

    // Inner class implements Iterator
    private class StackIterator implements Iterator {

        private int currentPosition;
        private T[] array;


        public StackIterator(T[] items) {
            this.array = items;
            this.currentPosition = 0;
        }

        @Override
        public boolean hasNext() {
            return this.currentPosition < StackGeneric.this.size;
        }

        @Override
        public T next() {
            if (this.hasNext()) {
                ++currentPosition;
                return array[currentPosition - 1];
            }
            throw new NoSuchElementException();
        }
    }
}

