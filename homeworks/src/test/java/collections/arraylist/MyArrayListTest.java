package collections.arraylist;

import static junit.framework.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

/***
 * Test Class MyArrayListTest
 *
 * @version 0.2
 * @since 07.04.2017
 */
public class MyArrayListTest {

    private MyArrayList<String> myArrayListTest;
    private Iterator<String> testIterable;
    private String arrayValue;
    private final String ADD_ITEM_START;
    private final String ADD_ITEM_CENTER;
    private final String ADD_ITEM_END;
    private final int SIZE_ARRAY;

    {
        SIZE_ARRAY = 13; // like be >= 13 for test of increase capacity
        ADD_ITEM_START = "ADD_ITEM_START";
        ADD_ITEM_CENTER = "ADD_ITEM_CENTER";
        ADD_ITEM_END = "ADD_ITEM_END";
        arrayValue = "Item number # ";
    }

    @Before
    public void before() throws Exception {
        myArrayListTest = new MyArrayList<>();
        testIterable = myArrayListTest.iterator();
        for (int index = 0; index < SIZE_ARRAY; index++) {
            myArrayListTest.add(arrayValue + index);
        }
    }

    @Test
    public void addTest() throws Exception {
        boolean actual = myArrayListTest.add(ADD_ITEM_END);
        assertTrue(actual);
    }

    @Test
    public void addIndexTest() throws Exception {
        boolean resultStart = myArrayListTest.add(0, ADD_ITEM_START);
        boolean resultCenter = myArrayListTest.add(SIZE_ARRAY / 2, ADD_ITEM_CENTER);
        boolean resultEnd = myArrayListTest.add(SIZE_ARRAY, ADD_ITEM_END);
        assertTrue(resultStart);
        assertTrue(resultCenter);
        assertTrue(resultEnd);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addWrongIndexTest() throws Exception {
        myArrayListTest.add(myArrayListTest.size() + 1, ADD_ITEM_END);
    }

    @Test
    public void setTest() throws Exception {
        boolean actual = myArrayListTest.set(0, ADD_ITEM_START);
        assertTrue(actual);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setWrongIndexTest() throws Exception {
        myArrayListTest.set(-1, ADD_ITEM_START);
    }

    @Test
    public void getTest() throws Exception {
        int indexItem = 5;
        String actual = myArrayListTest.get(indexItem);
        assertEquals(actual, arrayValue + indexItem);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getWrongIndexTest() throws Exception {
        myArrayListTest.get(-1);
    }

    @Test
    public void removeTest() throws Exception {
        int indexItem = 5;
        String actual = myArrayListTest.remove(indexItem);
        assertEquals(actual, arrayValue + indexItem);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeWrongIndexTest() throws Exception {
        myArrayListTest.remove(myArrayListTest.size() + 1);
    }

    @Test
    public void containsTest() throws Exception {
        String indexItem = "1";
        assertTrue(myArrayListTest.contains(arrayValue + indexItem));
    }

    @Test
    public void containsWrongTest() throws Exception {
        String indexItem = "wrong";
        assertFalse(myArrayListTest.contains(arrayValue + indexItem));
    }

    @Test
    public void sizeTest() throws Exception {
        assertEquals(myArrayListTest.size(), SIZE_ARRAY);
    }

    @Test
    public void iterableTest() throws Exception {
        for (int index = 0; testIterable.hasNext(); index++) {
            assertEquals(testIterable.next(), arrayValue + index);
        }
    }
}