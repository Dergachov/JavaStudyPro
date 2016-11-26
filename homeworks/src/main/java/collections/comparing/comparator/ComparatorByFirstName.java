package collections.comparing.comparator;

import java.util.Comparator;

/**
 * Created by Serezha on 2016-11-26.
 */
public class ComparatorByFirstName implements Comparator<EmployeeComparator> {
    @Override
    public int compare(EmployeeComparator o1, EmployeeComparator o2) {
        return o1.getNameFirst().compareTo(o2.getNameFirst());
    }
}
