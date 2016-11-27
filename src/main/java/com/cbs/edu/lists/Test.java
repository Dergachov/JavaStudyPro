package com.cbs.edu.lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        ArrayList<String> objects2 = new ArrayList<>();
        boolean add = objects2.add("1");
        objects2.add("2");
        objects2.add("3");
        objects2.add(2, "2*");
        System.out.println(objects2);

        System.out.println(objects2.get(2));

        String set = objects2.set(2, "2**");
        System.out.println(objects2);

        boolean remove = objects2.remove("3");
        String remove1 = objects2.remove(2);

        System.out.println(objects2.size());
        objects2.clear();
        System.out.println(objects2.size());

        List<String> objects = Arrays.asList("1", "2");

        if (objects2.containsAll(objects)) {

        }

        objects2.trimToSize();
    }
}
