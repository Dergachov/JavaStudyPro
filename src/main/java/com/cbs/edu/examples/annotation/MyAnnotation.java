package com.cbs.edu.examples.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.FIELD})
public @interface MyAnnotation {

    // enum, primitives, Class, String, <- arrays
    int count();

    int arg() default 0;
}
