package com.cbs.edu.examples.map.treemap;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class MethodsTest {
    public static void main(String[] args) {
        TreeMap<Integer, String> strings = new TreeMap<>();

        strings.put(1, "One");
        strings.put(2, "Two");
        strings.put(3, "Three");
        strings.put(4, "Four");
        strings.put(5, "Five");
        strings.put(7, "Seven");
        strings.put(9, "Nine");

        Map.Entry<Integer, String> integerStringEntry = strings.firstEntry();
        System.out.println(integerStringEntry.getValue());
        System.out.println(integerStringEntry.getKey());

        System.out.println(strings.higherEntry(3));
        System.out.println(strings.floorEntry(3));

        System.out.println(strings.subMap(3, 5));

        System.out.println(strings.pollFirstEntry());
        System.out.println(strings);

        System.out.println(strings.tailMap(3));
        System.out.println(strings.headMap(3));

        SortedMap<Integer, String> integerStringSortedMap = strings.tailMap(3);

        System.out.println(strings.ceilingEntry(5));
        System.out.println(strings.higherEntry(5));
    }
}
