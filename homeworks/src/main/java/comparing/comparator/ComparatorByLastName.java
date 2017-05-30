package comparing.comparator;

import java.util.Comparator;

/**
 * Created by Serezha on 2016-11-26.
 */
public class ComparatorByLastName implements Comparator<EmployeeComparator> {
    @Override
    public int compare(EmployeeComparator o1, EmployeeComparator o2) {
        return o1.getNameLast().compareTo(o2.getNameLast());
    }
}
