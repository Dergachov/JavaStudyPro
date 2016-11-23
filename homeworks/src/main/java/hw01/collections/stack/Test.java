package hw01.collections.stack;

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
    }
}
