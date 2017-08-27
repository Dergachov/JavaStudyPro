package com.cbs.edu.examples.annotation;

import java.lang.reflect.Field;

public class Test {
    public static void main(String[] args) throws NoSuchFieldException {

        MyEntity myEntity = new MyEntity("test");

        Class<? extends MyEntity> aClass = myEntity.getClass();

        if (aClass.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation annotation = aClass.getAnnotation(MyAnnotation.class);
            System.out.println(annotation.count());
        }

        Field field = aClass.getDeclaredField("field");

        field.setAccessible(true);

        if (field.isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation annotation = field.getAnnotation(MyAnnotation.class);
            System.out.println(annotation.arg());
        }
    }
}
