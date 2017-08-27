package com.cbs.edu.examples.reflex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Deprecated
public class Employee {

    private int cardID;
    private String nameFirst;
    private String nameLast;
    private int age;
    private double salary;

    private String saySecret(String name) {
        System.out.println(nameFirst + " " + name + " so secret!");
        return name;
    }
}
