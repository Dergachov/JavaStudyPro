package com.cbs.edu.examples.reflex;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.*;

public class ReflexTest {

    private Employee employee;
    Class<? extends Employee> aClass;

    @Before
    public void setUp() {
        employee = new Employee(100, "Serezha", "Dergachev", 25, 10000);
        aClass = employee.getClass();
    }

    @Test
    public void getClassInstance() {

        System.out.println(aClass.getName());
        System.out.println(aClass.getSimpleName());
    }

    @Test
    public void fuckEncapsulation() throws NoSuchFieldException, IllegalAccessException {
        System.out.println(employee.getCardID());

        Field cardID = aClass.getDeclaredField("cardID");
        cardID.setAccessible(true);
        cardID.setInt(employee, 777);

        System.out.println(employee.getCardID());
    }

    @Test
    public void getAllFields() throws IllegalAccessException {

        Field[] declaredFields = aClass.getDeclaredFields();

        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            System.out.println(declaredField.getName() + " " + declaredField.get(employee));
        }
    }

    @Test
    public void getType() throws IllegalAccessException, NoSuchFieldException {

        Field cardID = aClass.getDeclaredField("nameLast");
        cardID.setAccessible(true);

        Class<?> type = cardID.getType();
        System.out.println(type.getName());
    }

    @Test
    public void getModifiers() throws IllegalAccessException, NoSuchFieldException {

        Field cardID = aClass.getDeclaredField("nameLast");
        cardID.setAccessible(true);

        int modifiers = cardID.getModifiers();

        if (Modifier.isPrivate(modifiers)) {
            System.out.println("private");
        }
    }

    @Test
    public void getConstructors() throws IllegalAccessException, NoSuchFieldException, InvocationTargetException, InstantiationException {

        Constructor<?>[] constructors = aClass.getConstructors();

        Employee emp = (Employee) constructors[0].newInstance(100, "Serezha", "Dergachev", 25, 10000);

        System.out.println(emp);
    }

    @Test
    public void getMethods() {

        Method[] methods = aClass.getDeclaredMethods();

        for (Method method : methods) {
            Class<?> returnType = method.getReturnType();
            System.out.println(returnType.getName());
        }
    }

    @Test
    public void invokeMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method saySecret = aClass.getDeclaredMethod("saySecret", String.class);

        saySecret.setAccessible(true);

        Object arg = saySecret.invoke(employee, "arg");

        if (arg instanceof String) {

        }
    }

    @Test
    public void getClass2() throws ClassNotFoundException {
        Class<Employee> employeeClass = Employee.class;
        Class<?> aClass = Class.forName("A.java");
        System.out.println(aClass.getSimpleName());
    }
}
