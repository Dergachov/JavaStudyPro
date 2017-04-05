package com.cbs.edu.examples.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListIteratorTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("Hello1");
        list.add("Hello2");
        list.add("Hello3");
        list.add("Hello4");

        ListIterator<String> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }
    }
}
