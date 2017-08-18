package collections.examples;

import java.util.Comparator;

public class ComparatorByName implements Comparator<SomeObjectTest> {
    @Override
    public int compare(SomeObjectTest name1, SomeObjectTest name2) {
        return name1.getName().compareTo(name2.getName());
    }
}