package collections.examples;

import java.util.Objects;

public class SomeObjectTest implements Comparable<SomeObjectTest> {
    private String name;
    private int age;

    public SomeObjectTest(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(SomeObjectTest value) {
        return this.age - value.getAge();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SomeObjectTest)) return false;

        SomeObjectTest that = (SomeObjectTest) o;

        if (age != that.age) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
