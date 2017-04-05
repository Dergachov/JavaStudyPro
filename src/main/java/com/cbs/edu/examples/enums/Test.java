package com.cbs.edu.examples.enums;

public class Test {
    public static void main(String[] args) {
//        System.out.println(Country.USA);
//        System.out.println(Country.USA.getTitle());

        Country[] values = Country.values();
        for (Country value : values) {
            System.out.println(value.getTitle());
        }
    }
}
