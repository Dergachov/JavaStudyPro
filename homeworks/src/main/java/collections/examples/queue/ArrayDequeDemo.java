package collections.examples.queue;

import collections.examples.SomeObjectTest;

import java.util.ArrayDeque;

public class ArrayDequeDemo {
    public static void main(String[] args) {
        SomeObjectTest david = new SomeObjectTest("David", 20);
        SomeObjectTest anna = new SomeObjectTest("Anna", 22);
        SomeObjectTest olga = new SomeObjectTest("Olga", 24);

        ArrayDeque<SomeObjectTest> arrayDeque = new ArrayDeque<>();
        arrayDeque.addFirst(david);
        arrayDeque.addFirst(anna);
        arrayDeque.addLast(olga);
        System.out.println("Adds elements\n" + arrayDeque);

        //Add element in the HEAD queue
        arrayDeque.offerFirst(new SomeObjectTest("Alena", 30));
        System.out.println("\nAdd element in the HEAD\n" + arrayDeque);

        //Add element in the end queue
        arrayDeque.offerLast(new SomeObjectTest("Nikolas", 28));
        System.out.println("\nAdds elements\n" + arrayDeque);

        arrayDeque.removeFirst();
        arrayDeque.removeLast();
        System.out.println("\nAfter remove first and last element\n" + arrayDeque);

        System.out.println("\npeekFirst() " + arrayDeque.peekFirst());
        System.out.println("\npeekLast() " + arrayDeque.peekLast());

        arrayDeque.removeFirstOccurrence(anna);
        arrayDeque.removeLastOccurrence(olga);
        System.out.println("\nremoveFirstOccurrence(anna) & removeLastOccurrence(olga)" + arrayDeque);

        arrayDeque.addLast(anna);
        arrayDeque.addLast(olga);
        System.out.println("\nAdds 2 element in the end\n" + arrayDeque);

        System.out.println("\npollFirst() -> " + arrayDeque.pollFirst());
        System.out.println("\npollLast() -> " + arrayDeque.pollLast());

        System.out.println("\n " + arrayDeque);
    }
}
