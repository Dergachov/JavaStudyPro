package com.cbs.edu.map.hashmap;

import com.cbs.edu.comparing.Employee;

import java.util.HashMap;
import java.util.Map;

public class HMTest {
    public static void main(String[] args) {
        Employee e1 = new Employee("Ivan", 22);
        Employee e2 = new Employee("Ivan", 22);
        Employee e3 = new Employee("Ivan", 22);

//        System.out.println(e1.hashCode());
//        System.out.println(e2.hashCode());
//        System.out.println(e3.hashCode());

        Map<String, String> map = new HashMap<>();

        map.put("1", "One");
        map.put("1", "One");
        map.put("1", "One");
        map.put("2", "Two");
        map.put(null, "One");

        System.out.println(map.get("2"));
        System.out.println(map);
    }
}
