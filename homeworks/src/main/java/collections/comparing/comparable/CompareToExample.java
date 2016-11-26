package collections.comparing.comparable;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CompareToExample {

    public static void main(String[] args) {

        List<EmployeeCompareTo> listEmployee = new LinkedList<>();

        listEmployee.add(new EmployeeCompareTo(123789456, "Peter", "Jackson", 30, 5000));
        listEmployee.add(new EmployeeCompareTo(321789456, "Dora", "Norris", 25, 2000));
        listEmployee.add(new EmployeeCompareTo(123456987, "Peter", "O’Connor", 24, 8000));
        listEmployee.add(new EmployeeCompareTo(123654789, "Mark", "Jefferson", 22, 8000));
        listEmployee.add(new EmployeeCompareTo(123987654, "Mark", "McCoy", 35, 10000));
        listEmployee.add(new EmployeeCompareTo(987654321, "Robert", "Hall", 26, 6000));
        listEmployee.add(new EmployeeCompareTo(251287888, "Oliver", "Pitts", 33, 6000));
        listEmployee.add(new EmployeeCompareTo(891313545, "Ginger", "Edwards", 27, 1000));
        listEmployee.add(new EmployeeCompareTo(879117868, "Christine", "Rice", 28, 1000));
        listEmployee.add(new EmployeeCompareTo(238888789, "Juliet", "Manning", 22, 1000));

        System.out.println("Before sort \n" + listEmployee);
        Collections.sort(listEmployee);
        System.out.println("After sort \n" + listEmployee);
    }
}

