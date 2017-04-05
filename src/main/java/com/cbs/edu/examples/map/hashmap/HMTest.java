package com.cbs.edu.examples.map.hashmap;

import com.cbs.edu.examples.comparing.Employee;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HMTest {
    public static void main(String[] args) {
        Employee e1 = new Employee("Ivan", 22);
        Employee e2 = new Employee("Ivan", 22);
        Employee e3 = new Employee("Ivan", 22);

//        System.out.println(e1.hashCode());
//        System.out.println(e2.hashCode());
//        System.out.println(e3.hashCode());

        HashMap<String, String> map = new HashMap<>();

        map.put("1", "One");
        map.put("1", "One");
        map.put("1", "One");
        map.put("2", "Two");
        map.put(null, "One");

//        System.out.println(map.get("2"));
//        System.out.println(map);

        Set<Map.Entry<String, String>> entries = map.entrySet();

        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey() + " - " + next.getValue());
        }

        Set<String> strings = map.keySet();
        for (String key : strings) {
            System.out.println(key);
        }

        for (String value : map.values()) {
            System.out.println(value);
        }


    }
}
