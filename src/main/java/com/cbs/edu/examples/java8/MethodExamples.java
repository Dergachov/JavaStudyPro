package com.cbs.edu.examples.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.cbs.edu.examples.java8.MethodExamples.Gender.FEMALE;
import static com.cbs.edu.examples.java8.MethodExamples.Gender.MALE;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.Comparator.comparingInt;

public class MethodExamples {

    private List<Employee> employees;

    @Before
    public void setUp() {
        employees = asList(
                new Employee("Tom", 21, 20000, MALE, asList(2, 5, 12, 8)),
                new Employee("Anna", 36, 7000, FEMALE, asList(7, 11, 76, 14)),
                new Employee("Jack", 54, 30000, MALE, asList(54, 2, 122, 34)),
                new Employee("Helen", 18, 50000, FEMALE, asList(21, 2, 343, 121)),
                new Employee("Sara", 24, 50000, FEMALE, asList(23, 54, 1232, 12)),
                new Employee("Philip", 18, 100000, MALE, asList(1, 34, 45)),
//                new Employee("Philip", 18, 100000, MALE, asList(2, 5, 12, 8)),
                new Employee("Nixon", 43, 25000, MALE, asList(121, 45))
        );
    }

    public List<Integer> getIncrSalaries7(final List<Employee> employees) {
        final ArrayList<Integer> salaries = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.age < 30) {
                final int incrSalary = employee.getSalary() * 2;
                salaries.add(incrSalary);
            }
        }

        return salaries;
    }


    public List<Integer> getIncrSalaries8(final List<Employee> employees) {
        return employees // source
                .stream() // get Stream (1)
                .filter(e -> e.age < 30) // conveyor (N)
                .map(e -> e.salary * 2) // conveyor (N)
                .collect(Collectors.toList()); // terminal (1)
    }

    @Test
    public void tests() {
        System.out.println(getIncrSalaries7(employees));
        System.out.println(getIncrSalaries8(employees));
    }

    @Test
    public void streamOf() {
        Stream.of(1, 2, 3, 4, 5).filter(n -> n < 3).forEach(System.out::println);
    }

    @Test
    public void arraysStream() {
        stream(new int[]{1, 2, 3}).filter(n -> n < 3).forEach(System.out::println);
    }

    @Test
    public void fileStream() throws IOException {
        Files.lines(Paths.get("C:\\Users\\Evgeniy\\Desktop\\JavaStudyPro\\json_schema.json"), StandardCharsets.UTF_8)
                .forEach(System.out::println);
    }

    @Test
    public void chars() throws IOException {
        "Hello world".chars().forEach(System.out::println);
    }

    @Test
    public void builderStream() throws IOException {
        Stream.builder().add("a1").add("a2").add("a3").build().forEach(System.out::println);
    }

    // conveyor methods

    @Test
    public void filter() throws IOException {
        employees
                .stream()
                .filter(new Predicate<Employee>() {
                    @Override
                    public boolean test(Employee employee) {
                        return employee.gender == FEMALE;
                    }
                })
                .filter(e -> e.gender == FEMALE)
                .forEach(System.out::println);
    }

    @Test
    public void mapAndPeek() throws IOException {
        employees
                .stream()
                .map(employee -> employee.salary)
                .peek(integer -> integer *= 2)
                .forEach(System.out::println);
    }

    @Test
    public void skip() throws IOException {
        employees
                .stream()
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    public void distinct() throws IOException {
        employees
                .stream()
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void limit() throws IOException {
        employees
                .stream()
//                .sorted()
                .sorted(comparingInt(o -> o.salary))
                .filter(e -> e.gender == MALE)
                .limit(2)
                .forEach(System.out::println);

//        Collections.sort(employees);
    }

    @Test
    public void getMaxLuckyNum() throws IOException {
        employees
                .stream()
                .flatMap(employee -> employee.nums.stream().filter(n -> n < 50))
                .forEach(System.out::println);
    }

    @Test
    public void mapTo() throws IOException {
        employees
                .stream()
                .mapToInt(e -> e.salary)
                .forEach(System.out::println);
    }

    // terminal methods

    @Test
    public void forEach() throws IOException {
        employees
                .stream()
                .filter(e -> e.gender == FEMALE)
//                .forEach(employee -> System.out.println(employee));
                .forEach(System.out::println); // :: method reference
    }

    @Test
    public void collect() throws IOException {
        Map<String, Employee> collect = employees
                .stream()
                .filter(e -> e.gender == FEMALE)
//                .collect(Collectors.toSet());
//                .collect(Collectors.toList());
                .collect(Collectors.toMap(e -> e.name, e -> e));

        System.out.println(collect);
    }

    @Test
    public void min() throws IOException {
        employees
                .stream()
                .map(employee -> employee.salary)
//                .min(Comparator.naturalOrder()),
                .max(Comparator.naturalOrder());
    }

    @Test
    public void findFirst() throws IOException {
        employees
                .stream()
                .map(employee -> employee.salary)
                .findFirst();
    }

    @Test
    public void count() throws IOException {
        long count = employees
                .stream()
                .filter(e -> e.gender == FEMALE)
                .count();

        System.out.println(count);
    }

    @Test
    public void matches() throws IOException {
        boolean allMatch = employees
                .stream()
                .filter(e -> e.gender == FEMALE)
                .noneMatch(employee -> employee.salary > 70000);

        System.out.println(allMatch);
    }

    @Data
    @AllArgsConstructor
    public static class Employee implements Comparable<Employee>{
        private String name;
        private int age;
        private int salary;
        private Gender gender;
        private List<Integer> nums;

        @Override
        public int compareTo(Employee o) {
            return o.salary - this.salary;
        }
    }

    enum Gender {
        MALE, FEMALE
    }
}
