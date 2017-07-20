package comparing.comparator;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Serezha on 2016-11-26.
 */
public class ComparatorByCard implements Comparator<EmployeeComparator>, Serializable {

    @Override
    public int compare(EmployeeComparator Object1, EmployeeComparator Object2) {
        Integer iObject1 = new Integer(Object1.getCardID());
        Integer iObject2 = new Integer(Object2.getCardID());
        return iObject1.compareTo(iObject2);
    }
}
