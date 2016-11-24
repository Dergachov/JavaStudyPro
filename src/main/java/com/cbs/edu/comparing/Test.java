package com.cbs.edu.comparing;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Avan", 23),
                new Employee("Xvan", 13),
                new Employee("Zvan", 33),
                new Employee("Hvan", 56)
        );

        System.out.println(employees);

//        Collections.sort(employees);
//        Collections.sort(employees, new SortByEmployeeNameComparator());

        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

//        Collections.sort(employees, (o1, o2) -> o1.getName().compareTo(o2.getName()));


        System.out.println(employees);
    }
}
