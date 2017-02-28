package collections.arraylist;

/**
 * Created by serezha on 28.02.17.
 */

public class TestMyArrayList {
    public static void main(String[] args) {

        //StackGeneric<Employee> stackNoSize = new StackGeneric<>();

        MyArrayList<String> test = new MyArrayList<>(2);
        //MyArrayList<String> test2 = new MyArrayList<>(0);

        System.out.println(test.getSize());
        System.out.println(test.getCursor());
        test.add("String text 1");
        test.add("String text 2");
        test.add("String text 3");
        test.add("String text 4");
        test.add(0,"String text 5");
        test.set(1,"Hello world!!!");
        System.out.println(test.getCursor());
        System.out.println(test.get(0));
        System.out.println(test.get(1));
        System.out.println(test.get(2));
        System.out.println(test.get(3));
        //System.out.println(test.getData(4));
    }
}
