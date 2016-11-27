package com.cbs.edu.collections.my_link_list;

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import static org.junit.Assert.*;

public class LinkedListTest {

    private LinkedList<String> linkedList;
    private String testValue;

    @Before
    public void setUp() {
        linkedList = new LinkedList<>();
        testValue = "Hello world!";
    }

    @Test
    public void size() throws Exception {
        final int EXPECTED_SIZE_VALUE = 10;
        for (int i = 0; i < EXPECTED_SIZE_VALUE; i++) {
            linkedList.add(testValue);
        }
        final int ACTUAL_SIZE_VALUE = linkedList.size();
        assertEquals(EXPECTED_SIZE_VALUE, ACTUAL_SIZE_VALUE);
    }

    @Test
    public void add() {
        linkedList.add(testValue);
        final int INSERT_INDEX = 0;
        final String ACTUAL_RESULT = linkedList.get(INSERT_INDEX);
        assertEquals(testValue, ACTUAL_RESULT);
    }

    @Test
    public void addToIndex() {
        final int PRE_SIZE = 10;
        for (int i = 0; i < PRE_SIZE; i++) {
            linkedList.add(testValue);
        }
        final int INSERT_INDEX = 5;
        linkedList.add(INSERT_INDEX, testValue);
        final String ACTUAL_RESULT = linkedList.get(INSERT_INDEX);
        assertEquals(testValue, ACTUAL_RESULT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addToNonExistentIndex() throws Exception {
        final int NOT_EXISTENT_INDEX = linkedList.size() + 100;
        linkedList.add(NOT_EXISTENT_INDEX, testValue);
    }

    @Test
    public void containsTrue() throws Exception {
        linkedList.add(testValue);
        final boolean ACTUAL_RESULT = linkedList.contains(testValue);
        assertTrue(ACTUAL_RESULT);
    }

    @Test
    public void containsFalse() throws Exception {
        final String FALSY_VALUE = "Something..";
        linkedList.add(FALSY_VALUE);
        final boolean ACTUAL_RESULT = linkedList.contains(testValue);
        assertFalse(ACTUAL_RESULT);
    }

    @Test
    public void get() throws Exception {
        final int PRE_SIZE = 10;
        for (int i = 0; i < PRE_SIZE; i++) {
            linkedList.add(testValue);
        }
        final int START = 0;
        final int MIDDLE = PRE_SIZE / 2;
        final int END = PRE_SIZE - 1;
        assertEquals(testValue, linkedList.get(START));
        assertEquals(testValue, linkedList.get(MIDDLE));
        assertEquals(testValue, linkedList.get(END));
    }

    @Test
    public void set() throws Exception {
        final String UPDATED_VALUE = "Hello Earth!";
        final int INSERT_INDEX = 0;
        linkedList.add(testValue);
        linkedList.set(INSERT_INDEX, UPDATED_VALUE);
        final String RESULT = linkedList.get(INSERT_INDEX);
        assertEquals(UPDATED_VALUE, RESULT);
    }

    @Test
    public void remove() throws Exception {
        final int PRE_SIZE = 10;
        final int REMOVED_INDEX = 0;
        for (int i = 0; i < PRE_SIZE; i++) {
            linkedList.add(testValue);
        }
        linkedList.remove(REMOVED_INDEX);
        assertEquals(PRE_SIZE - 1, linkedList.size());
    }

    @Test
    public void iterator() throws Exception {
        Iterator<String> iterator = linkedList.iterator();
        assertNotNull(iterator);
    }
}