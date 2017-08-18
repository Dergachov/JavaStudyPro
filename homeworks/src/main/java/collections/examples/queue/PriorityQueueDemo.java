package collections.examples.queue;

import collections.examples.SomeObjectTest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class PriorityQueueDemo {
    public static void main(String[] args) {
        //if uses some your objects you need implements Comparable for it.
        PriorityQueue<SomeObjectTest> priorityQueue = new PriorityQueue<>();

        //.add() Inserts the specified element into this priority queue.
        priorityQueue.add(new SomeObjectTest("David", 20));
        priorityQueue.add(new SomeObjectTest("Anna", 22));
        priorityQueue.add(new SomeObjectTest("Olga", 24));
        priorityQueue.add(new SomeObjectTest("Nikolas", 28));

        System.out.println("Before uses .add()\n" + priorityQueue);

        /*.offer() Inserts the specified element into this priority queue.
        Add element in the HEAD queue.*/
        priorityQueue.offer(new SomeObjectTest("Nikita", 29));
        System.out.println("Before uses .offer()\n" + priorityQueue);

        //Retrieves, but does not remove, the HEAD of this queue, or returns null if this queue is empty.
        System.out.println("Before uses .peek()\n" + priorityQueue.peek().toString());

        //Retrieves and removes the head of this queue, or returns null if this queue is empty.
        System.out.println("Uses .poll()\n" + priorityQueue.poll().toString());
        System.out.println("Before uses .poll()\n" + priorityQueue);

        SomeObjectTest[] arrayQT = new SomeObjectTest[5];
        priorityQueue.toArray(arrayQT);
        System.out.println("Before uses .toArray()\n" + Arrays.toString(arrayQT));

        System.out.println("Uses iterator\n");
        Iterator<SomeObjectTest> itr = priorityQueue.iterator();
        while (itr.hasNext()) {
            Object object = itr.next();
            System.out.println(object);
        }
        Comparator comparator = priorityQueue.comparator();
        if (comparator == null) {
            System.out.println("\nIs sorted according to the natural ordering of its elements.");
        }

        priorityQueue.clear();
        System.out.println("After .clear() size = " + priorityQueue.size());
    }
}
