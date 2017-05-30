package collections.linkedlist;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test Class MyArrayListTest
 *
 * @version 0.1
 * @since 09.04.2017
 */

public class MyLinkedListTest {

    private MyLinkedList<String> myLinkedListTest;
    private String linkedListValue;
    private int NUMBER_ELEMENTS;
    private String ADD_ITEM_BEGIN;
    private String ADD_ITEM_CENTER;
    private String ADD_ITEM_END;

    @Before
    public void before() {

        linkedListValue = "Item number # ";
        NUMBER_ELEMENTS = 3;
        ADD_ITEM_BEGIN = "ADD_ITEM_BEGIN";
        ADD_ITEM_CENTER = "ADD_ITEM_CENTER";
        ADD_ITEM_END = "ADD_ITEM_END";

        myLinkedListTest = new MyLinkedList<>();
        for (int i = 0; i < NUMBER_ELEMENTS; i++) {
            myLinkedListTest.add(linkedListValue + i);
        }
    }

    @Test
    public void addTest() throws Exception {
        boolean result = myLinkedListTest.add(ADD_ITEM_END);
        assertTrue(result);
    }

    @Test
    public void addIndexTest() throws Exception {
        boolean resultBegin = myLinkedListTest.add(0, ADD_ITEM_BEGIN);
        boolean resultCenter = myLinkedListTest.add(myLinkedListTest.size() / 2, ADD_ITEM_CENTER);
        boolean resultEnd = myLinkedListTest.add(myLinkedListTest.size() - 1, ADD_ITEM_END);

        assertTrue(resultBegin);
        assertTrue(resultCenter);
        assertTrue(resultEnd);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addWrongIndexTest() throws Exception {
        myLinkedListTest.add(-1, ADD_ITEM_BEGIN);
    }

    @Test
    public void getTest() throws Exception {
        int indexValue = 0;
        String getBeginValue = myLinkedListTest.get(indexValue);
        assertEquals(getBeginValue, linkedListValue + indexValue);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getWrongTest() throws Exception {
        myLinkedListTest.add(myLinkedListTest.size(), ADD_ITEM_BEGIN);
    }

    @Test
    public void setTest() throws Exception {
        int indexValue = 1;
        String setValue = myLinkedListTest.set(indexValue, ADD_ITEM_CENTER);
        assertEquals(setValue, linkedListValue + indexValue);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setWrongTest() throws Exception {
        myLinkedListTest.set(myLinkedListTest.size() + 1, ADD_ITEM_BEGIN);
    }

    @Test
    public void containsTest() throws Exception {
        int indexValue = 0;
        boolean result = myLinkedListTest.contains(linkedListValue + indexValue);
        assertTrue(result);
    }

    @Test
    public void containsWrongTest() throws Exception {
        boolean result = myLinkedListTest.contains("NOT_CONTAINED");
        assertFalse(result);
    }

    @Test
    public void removeTest() throws Exception {
        int indexValue = 0;
        String removeBeginValue = myLinkedListTest.remove(indexValue);
        assertEquals(removeBeginValue, linkedListValue + indexValue);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeWrongTest() throws Exception {
        myLinkedListTest.remove(myLinkedListTest.size() + 1);
    }

    @Test
    public void sizeTest() throws Exception {
        int currentSize = myLinkedListTest.size();
        assertEquals(currentSize, NUMBER_ELEMENTS);
    }

}