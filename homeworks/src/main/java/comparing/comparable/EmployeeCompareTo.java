package comparing.comparable;

/**
 * If need using easy simple sort then then you must use implements Comparable<T>
 */
public class EmployeeCompareTo implements Comparable<EmployeeCompareTo> {
    private int cardID;
    private String nameFirst;
    private String nameLast;
    private int age;
    private double salary;

    public EmployeeCompareTo(int cardID, String nameFirst, String nameLast, int age, double salary) {
        this.cardID = cardID;
        this.nameFirst = nameFirst;
        this.nameLast = nameLast;
        this.age = age;
        this.salary = salary;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    public String getNameLast() {
        return nameLast;
    }

    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Simple compareTo implements return high salary.
     */
    @Override
    public int compareTo(EmployeeCompareTo object) {
        return (int) object.salary - (int) this.salary;
    }

    @Override
    public String toString() {
        return "EmployeeComparator{"
                + "cardID=" + cardID
                + ", nameFirst='" + nameFirst + '\''
                + ", nameLast='" + nameLast + '\''
                + ", age=" + age
                + ", salary=" + salary
                + '}' + "\n";
    }
}
