package collections.examples.set;

import collections.examples.ComparatorByName;
import collections.examples.SomeObjectTest;

import java.util.*;

public class TreeSetDemo {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random(System.currentTimeMillis());
        int[] ints = new int[11];
        String[] strs = new String[11];

        TreeSet<SomeObjectTest> setObj = new TreeSet<>();
        TreeSet<Integer> setInt = new TreeSet<>();
        TreeSet<String> setStr = new TreeSet<>();
        for (int i = 1; i <= 10; i++) {
            int num = random.nextInt(100);
            ints[i] = num;
            strs[i] = "String " + num;
            setInt.add(num);
            setStr.add("String " + num);
            setObj.add(new SomeObjectTest("String " + num, num));
        }
        TreeSet<SomeObjectTest> setObjComparator = new TreeSet<>(new ComparatorByName());
        for (SomeObjectTest aSetObj : setObj) {
            setObjComparator.add(aSetObj);
        }
        System.out.println("TreeSet sorted like compareTo() in object " + setObj);
        System.out.println("TreeSet sorted with ComparatorByName() " + setObjComparator);
        System.out.println("Not sorted nums " + Arrays.toString(ints));
        System.out.println("Nums after add in TreeSet" + setInt);
        System.out.println("Not sorted strings " + Arrays.toString(strs));
        System.out.println("Strings after add in TreeSet" + setStr);

    }
}
