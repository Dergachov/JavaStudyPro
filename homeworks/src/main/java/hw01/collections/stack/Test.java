package hw01.collections.stack;

import java.util.Iterator;

/**
 * Created by Serezha on 2016-11-22.
 */
public class Test {
    public static void main(String[] args) {
        StackGeneric<String> s = new StackGeneric<>(100);
        for (int i = 1; i <= 140; i++) {
            //System.out.println(i);
            s.push("String item #" + i);
        }
        System.out.println("\nStack size  " + s.getSize() + "\nEmpty cells " + s.getCountUnusedArrayCells() + "\nReal sArray size " + s.getArrSize() + "\n");
        for (int i = 1; i <= 80; i++) {
            //System.out.println(i);
            System.out.println("pop(): " + s.pop());
        }
        System.out.println("\nStack size  " + s.getSize() + "\nEmpty cells " + s.getCountUnusedArrayCells() + "\nReal sArray size " + s.getArrSize() + "\n");

        Iterator<String> iterator = s.iterator();
        while (iterator.hasNext()) {
            String cur = iterator.next();
            System.out.println("Used iterator -> " + cur);
        }

    }
}
