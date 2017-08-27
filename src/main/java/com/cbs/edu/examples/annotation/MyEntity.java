package com.cbs.edu.examples.annotation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@MyAnnotation(count = 7)
public class MyEntity {

    @MyAnnotation(count = 777, arg = 77)
    public String field;

//    @MyAnnotation
    public void f() {

    }
}
