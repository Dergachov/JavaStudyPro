package reflex.json_builder.demo.employee;

import reflex.json_builder.JsonBuild;
import reflex.json_builder.JsonBuildField;

import java.util.Arrays;

@JsonBuild(objectName = "employees")
public class Employee {
    @JsonBuildField
    private String firstName;
    @JsonBuildField
    private String lastName;
    @JsonBuildField
    private int age;
    @JsonBuildField
    private Address address;
    @JsonBuildField
    private String[] phones;


    public Employee(String firstName, String lastName, int age, Address address, String[] phones) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.phones = phones;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String[] getPhones() {
        return phones;
    }

    public void setPhones(String[] phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", address=" + address +
                ", phones=" + Arrays.toString(phones) +
                '}';
    }
}