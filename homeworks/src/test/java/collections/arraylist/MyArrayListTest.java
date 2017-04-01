package collections.arraylist;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class MyArrayListTest {

    private MyArrayList<String> myArrayListTest = new MyArrayList<>();

    @Before
    public void setUp() throws Exception {
        System.out.println("Start size of data = " + myArrayListTest.size());
        System.out.println("Start length of data = " + myArrayListTest.length());
        for (int i = 1; i < 21; i++) {
            myArrayListTest.add("Element " + i);
        }
    }

    @Test
    public void testAdd() throws Exception {
        for (int i = 1; i < 11; i++) {
            myArrayListTest.add("Default Add Element (must be end)" + i);
        }
    }

    @Test
    public void testAddIndex() throws Exception {
        for (int i = 0; i < 5; i++) {
            myArrayListTest.add(0, "AddIndex(0) element in the Begin");
            myArrayListTest.add((myArrayListTest.length() / 2), "AddIndex((myarraylist.length() / 2)) element in the Middle");
            myArrayListTest.add(myArrayListTest.length() - 1, "AddIndex(myarraylist.length() - 1) element in the End");
        }
    }

    @Test
    public void testSet() throws Exception {
        myArrayListTest.set(0,"Change element testSet(0)");
        myArrayListTest.set((myArrayListTest.length() / 2),"Change element in the End Middle testSet(myarraylist.length() / 2)");
        myArrayListTest.set(myArrayListTest.length() - 1,"Change element in the End testSet(myarraylist.length() - 1)");
    }

    @Test
    public void testRemove() throws Exception {
        for (int i = 0; i < 5; i++) {
            myArrayListTest.remove(0);
            myArrayListTest.remove(myArrayListTest.length() / 2);
            myArrayListTest.remove(myArrayListTest.length() - 1);
        }
    }

    @Test
    public void testContains() throws Exception {
        System.out.println(" contains '50'" + myArrayListTest.contains("50"));
        System.out.println(" contains '10'" + myArrayListTest.contains("Element 10"));
        System.out.println(" contains 'Element'" + myArrayListTest.contains("Element"));
    }

    @Test
    public void testIterator() throws Exception {
        Iterator<String> iterator = myArrayListTest.iterator();
        while (iterator.hasNext()) {
            String current = iterator.next();
            System.out.println(" iterator.next()-> " + current);
        }
    }
    @After
    public void showResults () throws Exception {
        System.out.println("After tests manipulation: ");
        for (int i = 0; i < myArrayListTest.length(); i++) {
            System.out.println(myArrayListTest.get(i));
        }
        System.out.println("Length of data = " + myArrayListTest.length());
        System.out.println("Size of array = " + myArrayListTest.size());
    }
}