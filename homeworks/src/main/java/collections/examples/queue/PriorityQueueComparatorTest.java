package collections.examples.queue;

import collections.examples.ComparatorByAge;
import collections.examples.QueueTest;

import java.util.PriorityQueue;

public class PriorityQueueComparatorTest {
    public static void main(String[] args) {
        PriorityQueue<QueueTest> priorityQueue1 = new PriorityQueue<>(4, new ComparatorByAge());
        priorityQueue1.add(new QueueTest("Alla", 25));
        priorityQueue1.add(new QueueTest("Olga", 28));
        priorityQueue1.add(new QueueTest("Kristina", 24));
        priorityQueue1.add(new QueueTest("Elena", 18));
        System.out.println("With size and ComparatorByAge():\n" + priorityQueue1);

        PriorityQueue<QueueTest> priorityQueue2 = new PriorityQueue<>();
        priorityQueue2.add(new QueueTest("David", 20));
        priorityQueue2.add(new QueueTest("Anna", 22));
        priorityQueue2.add(new QueueTest("Olga", 24));
        priorityQueue2.add(new QueueTest("Nikolas", 28));
        System.out.println("Without ComparatorByAge():\n" + priorityQueue2);

        PriorityQueue<QueueTest> sortedByAge = new PriorityQueue<>(priorityQueue2.size(), new ComparatorByAge());
        for (QueueTest aPriorityQueue2 : priorityQueue2) {
            sortedByAge.add(aPriorityQueue2);
        }
        System.out.println("With ComparatorByAge():\n" + sortedByAge);
    }
}
