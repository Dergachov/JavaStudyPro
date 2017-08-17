package collections.examples;

public class QueueTest implements Comparable<QueueTest> {
    private String name;
    private int age;

    public QueueTest(String name, int age) {
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

    //Will sort by name in queue
    @Override
    public int compareTo(QueueTest value) {
        return this.name.compareTo(value.getName());
    }
}
