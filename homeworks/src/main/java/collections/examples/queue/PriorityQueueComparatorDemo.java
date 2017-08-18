package collections.examples.queue;

import collections.examples.ComparatorByName;
import collections.examples.SomeObjectTest;

import java.util.PriorityQueue;

public class PriorityQueueComparatorDemo {
    public static void main(String[] args) {
        PriorityQueue<SomeObjectTest> priorityQueue1 = new PriorityQueue<>(4, new ComparatorByName());
        priorityQueue1.add(new SomeObjectTest("Alla", 25));
        priorityQueue1.add(new SomeObjectTest("Olga", 28));
        priorityQueue1.add(new SomeObjectTest("Kristina", 24));
        priorityQueue1.add(new SomeObjectTest("Elena", 18));
        System.out.println("With size and ComparatorByName():\n" + priorityQueue1);

        PriorityQueue<SomeObjectTest> priorityQueue2 = new PriorityQueue<>();
        priorityQueue2.add(new SomeObjectTest("David", 20));
        priorityQueue2.add(new SomeObjectTest("Anna", 22));
        priorityQueue2.add(new SomeObjectTest("Olga", 24));
        priorityQueue2.add(new SomeObjectTest("Nikolas", 28));
        System.out.println("Without ComparatorByName():\n" + priorityQueue2);

        PriorityQueue<SomeObjectTest> sortedByName = new PriorityQueue<>(priorityQueue2.size(), new ComparatorByName());
        for (SomeObjectTest aPriorityQueue2 : priorityQueue2) {
            sortedByName.add(aPriorityQueue2);
        }
        System.out.println("With ComparatorByName():\n" + sortedByName);
    }
}
