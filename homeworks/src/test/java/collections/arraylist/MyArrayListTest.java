package collections.arraylist;

import static junit.framework.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

/***
 * class MyArrayListTest
 *
 * @version 0.2
 * @since 04.04.2017
 */
public class MyArrayListTest {

    private MyArrayList<String> myArrayListTest;
    private static Iterator<String> testIterable;
    private static String valueArrayItems = "Item number # ";
    private static final String addItemStart = "addItemStart";
    private static final String addItemCenter = "addItemCenter";
    private static final String addItemEnd = "addItemEnd";
    private static final int numberArrayItems = 13;
    private static int counter;

    @Before
    public void init() throws Exception {

        myArrayListTest = new MyArrayList<>();
        testIterable = myArrayListTest.iterator();

        /***
         * There is test for method add();
         */
        for (int numeration = 0; numeration < numberArrayItems; numeration++) {
            assertTrue(myArrayListTest.add(valueArrayItems + numeration));
        }
        assertEquals(myArrayListTest.size(), numberArrayItems);
    }

    @Test
    public void testMethods() throws Exception {

        /***
         * There is test for method contains();
         */
        for (int index = 0; index < numberArrayItems; index++) {
            assertFalse(myArrayListTest.contains(valueArrayItems));
            assertTrue(myArrayListTest.contains(valueArrayItems + index));
        }

        /***
         * There are test for methods iterator(); get();
         */
        while (testIterable.hasNext()) {
            assertEquals(testIterable.next(), myArrayListTest.get(counter));
            ++counter;
        }

        /***
         * There are test for methods add(index); get();
         */
        assertTrue(myArrayListTest.add(0, addItemStart));
        assertEquals(myArrayListTest.get(0), addItemStart);

        assertTrue(myArrayListTest.add(myArrayListTest.size() / 2, addItemCenter));
        assertEquals(myArrayListTest.get(myArrayListTest.size() / 2), addItemCenter);

        assertTrue(myArrayListTest.add(myArrayListTest.size(), addItemEnd));
        assertEquals(myArrayListTest.get(myArrayListTest.size() - 1), addItemEnd);

        /***
         * There is test for method remove(index);
         */
        for (int index = 0; index < myArrayListTest.size(); index++) {
            if (myArrayListTest.get(index).equals(addItemStart)) {
                assertEquals(addItemStart, myArrayListTest.remove(index));
            } else if (myArrayListTest.get(index).equals(addItemCenter)) {
                assertEquals(addItemCenter, myArrayListTest.remove(index));
            } else if (myArrayListTest.get(index).equals(addItemEnd)) {
                assertEquals(addItemEnd, myArrayListTest.remove(index));
            }
        }
        assertEquals(myArrayListTest.size(), numberArrayItems);

        /***
         * There are test for methods set(index); remove(index);
         */
        assertTrue(myArrayListTest.set(0, addItemStart));
        assertEquals(myArrayListTest.get(0), addItemStart);
        assertEquals(myArrayListTest.remove(0), addItemStart); // lastDeletingItems

        assertTrue(myArrayListTest.set(myArrayListTest.size() / 2, addItemCenter));
        assertEquals(myArrayListTest.get(myArrayListTest.size() / 2), addItemCenter);
        assertEquals(myArrayListTest.remove(myArrayListTest.size() / 2), addItemCenter); // lastDeletingItems

        assertTrue(myArrayListTest.set(myArrayListTest.size(), addItemEnd));
        assertEquals(myArrayListTest.get(myArrayListTest.size()), addItemEnd);
        assertEquals(myArrayListTest.remove(myArrayListTest.size()), addItemEnd); // lastDeletingItems

        int lastDeletingItems = 3;
        assertEquals(myArrayListTest.size(), numberArrayItems - lastDeletingItems);
    }
}