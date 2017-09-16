package com.cbs.edu.examples.java8;

public class ITest {
    public static void main(String[] args) {
//        A a = new A();

        A b = new B();

        b.d();
        A.s();
    }

    @FunctionalInterface
    interface A {
        public static final String SOME_CONST = ""; // Java 7

        public abstract void f(); // Java 7

//        void f2();

        public default void d() {
            System.out.println("Welcome to Java 8!"); // Java 8
        }

        public static void s() {} // Java 8
    }

    static class B implements A {

        @Override
        public void f() {

        }

        @Override
        public void d() {

        }
    }
}
