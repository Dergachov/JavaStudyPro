package reflex.json_builder.demo;

import reflex.json_builder.JsonBuilder;
import reflex.json_builder.demo.employee.Address;
import reflex.json_builder.demo.employee.Employee;

public class JsonBuilderObjectSerializationDemo {
    public static void main(String[] args) {
        Employee[] employees = new Employee[2];
        Employee employee1 = new Employee("Reese", "Witherspoon", 28, new Address("North ave", 2345),
                new String[]{"+184708945", "+174208984"});
        Employee employee2 = new Employee("Nat", "Wolff", 34, new Address("East ave", 184),
                new String[]{"+184451378", "+164202943"});
        employees[0] = employee1;
        employees[1] = employee2;

        //Serialization object of employee
        JsonBuilder doc = new JsonBuilder();
        try {
            doc.serialization(employee1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("Serialization object:");
        System.out.println(doc.toString());
        System.out.println();

        //Serialization array of employees
        JsonBuilder doc2 = new JsonBuilder();
        try {
            doc2.serialization(employees);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Serialization array objects:");
        System.out.println(doc2.toString());
    }
}