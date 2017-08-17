package collections.examples;

import java.util.Comparator;

public class ComparatorByAge implements Comparator<QueueTest> {
    @Override
    public int compare(QueueTest age1, QueueTest age2) {
        if (age1.getAge() == age2.getAge()) {
            return 0;
        } else if (age1.getAge() < age2.getAge()) {
            return -1;
        } else {
            return 1;
        }
    }
}