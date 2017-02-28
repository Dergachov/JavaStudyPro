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
        test.addData("String text 1");
        test.addData("String text 2");
        test.addData("String text 3");
        test.addData("String text 4");
        test.addData(0,"String text 5");
        test.setData(1,"Hello world!!!");
        System.out.println(test.getCursor());
        System.out.println(test.getData(0));
        System.out.println(test.getData(1));
        System.out.println(test.getData(2));
        System.out.println(test.getData(3));
        //System.out.println(test.getData(4));
    }
}
