package collections.comparing.comparator;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ComparatorMain {

    public static void main(String[] args) {

        List<EmployeeComparator> listEmployee = new LinkedList<>();

        listEmployee.add(new EmployeeComparator(123789456, "Peter", "Jackson", 30, 5000));
        listEmployee.add(new EmployeeComparator(321789456, "Dora", "Norris", 25, 2000));
        listEmployee.add(new EmployeeComparator(123456987, "Peter", "O’Connor", 24, 8000));
        listEmployee.add(new EmployeeComparator(123654789, "Mark", "Jefferson", 22, 8000));
        listEmployee.add(new EmployeeComparator(123987654, "Mark", "McCoy", 35, 10000));
        listEmployee.add(new EmployeeComparator(987654321, "Robert", "Hall", 26, 6000));
        listEmployee.add(new EmployeeComparator(251287888, "Oliver", "Pitts", 33, 6000));
        listEmployee.add(new EmployeeComparator(891313545, "Ginger", "Edwards", 27, 1000));
        listEmployee.add(new EmployeeComparator(879117868, "Christine", "Rice", 28, 1000));
        listEmployee.add(new EmployeeComparator(238888789, "Juliet", "Manning", 22, 1000));

        System.out.println("First list condition \n" + listEmployee);

        Collections.sort(listEmployee, new ComparatorByCard());
        System.out.println("Sort by ComparatorByCard \n" + listEmployee);

        Collections.sort(listEmployee, new ComparatorByFirstName());
        System.out.println("Sort by ComparatorByFirstName \n" + listEmployee);

        Collections.sort(listEmployee, new ComparatorByLastName());
        System.out.println("Sort by ComparatorByLastName \n" + listEmployee);

        Collections.sort(listEmployee, new ComparatorBySalary());
        System.out.println("Sort by ComparatorBySalary \n" + listEmployee);
    }
}

