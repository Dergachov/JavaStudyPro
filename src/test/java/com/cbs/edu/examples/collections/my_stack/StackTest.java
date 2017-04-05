package com.cbs.edu.examples.collections.my_stack;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * http://devcolibri.com/864
 */
public class StackTest {

    private Stack<String> stack;
    private String testData;

    @BeforeClass // connection db...
    public static void setUp() {

    }

    @Before
    public void setUpBefore() {
        stack = new Stack<>();
        testData = "Hello world!";
    }

    @Test
    public void pushTest() {
        stack.push(testData);
        String result = stack.pop();
        assertEquals(testData, result);
    }

    @Test
    public void sizeTest() {
        stack.push(testData);
        assertTrue("Bad size", stack.getSize() == 1);
    }

    @Test
    public void pop() throws Exception {

    }

    @Test(expected = ArithmeticException.class)
    public void exceptionTest() {
        System.out.println(10 / 0);
    }

}