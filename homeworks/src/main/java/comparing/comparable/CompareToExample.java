package comparing.comparable;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CompareToExample {

    public static void main(String[] args) {

        List<EmployeeCompareTo> listEmployee = new LinkedList<>();
        final int id1 = 123789456;
        final int id2 = 321789456;
        final int id3 = 321789456;

        int id1_years = 30;
        int id2_years = 28;
        int id3_years = 25;

        int id1_salary = 5000;
        int id2_salary = 4000;
        int id3_salary = 8000;

        listEmployee.add(new EmployeeCompareTo(id1, "Peter", "Jackson", id1_years, id1_salary));
        listEmployee.add(new EmployeeCompareTo(id2, "Dora", "Norris", id2_years, id2_salary));
        listEmployee.add(new EmployeeCompareTo(id3, "Peter", "O’Connor", id3_years, id3_salary));

        System.out.println("Before sort \n" + listEmployee);
        Collections.sort(listEmployee);
        System.out.println("After sort \n" + listEmployee);
    }
}

