package com.cbs.edu.examples.map.treemap;

import java.util.Comparator;
import java.util.TreeMap;

public class Test {
    public static void main(String[] args) {
        TreeMap<String, Object> strings = new TreeMap<>();

        TreeMap<Employee, Integer> employees = new TreeMap<>(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return 0;
            }
        });

        employees.put(new Employee(), 1);
        employees.put(new Employee(), 2);
    }

    static class Employee {

    }
}
