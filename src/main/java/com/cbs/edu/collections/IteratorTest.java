package com.cbs.edu.collections;

import java.util.*;

public class IteratorTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("Hello1");
        list.add("Hello2");
        list.add("Hello3");
        list.add("Hello4");

        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            String value = iterator.next();
            System.out.println(value);
        }
    }
}
