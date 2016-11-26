package collections.comparing.comparator;

import java.util.Comparator;

/**
 * Created by Serezha on 2016-11-26.
 */
public class ComparatorBySalary implements Comparator<EmployeeComparator> {
    @Override
    public int compare(EmployeeComparator object1, EmployeeComparator object2) {
        Double dObject1 = new Double(object1.getSalary());
        Double dObject2 = new Double(object2.getSalary());
        return dObject1.compareTo(dObject2);
    }
}
