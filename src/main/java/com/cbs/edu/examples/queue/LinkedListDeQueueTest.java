package com.cbs.edu.examples.queue;

import java.util.Deque;
import java.util.LinkedList;

public class LinkedListDeQueueTest {
    public static void main(String[] args) {

        Deque<String> queue = new LinkedList<>();

        queue.add("A");
        queue.add("B");
        queue.add("C");
        queue.add("D");
        queue.add("E");

        System.out.println(queue);

//        System.out.println(queue.element());
        System.out.println(queue.element());

        System.out.println(queue);
    }
}
