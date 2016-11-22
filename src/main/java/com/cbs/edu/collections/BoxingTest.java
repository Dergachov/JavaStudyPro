package com.cbs.edu.collections;

import java.util.ArrayList;
import java.util.List;

public class BoxingTest {
    public static void main(String[] args) {
        // TODO: 22.11.2016 Todo example?
        List<Integer> numbers = new ArrayList<>();
        int k = 20;

        numbers.add(10); // numbers.add(new Integer(10)); // boxing
        int i1 = Integer.parseInt("101");
        int i2 = Integer.parseInt("101", 2);
        int i3 = Integer.parseInt("ABC", 16);
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);
        System.out.println(Integer.toHexString(2748));

        numbers.add(k);

        Integer j = new Integer(10);
        int j1 = j; // j1 = Integer.valueOf(k); // unboxing
    }
}
