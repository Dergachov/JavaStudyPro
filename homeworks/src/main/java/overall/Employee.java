package overall;

/**
 * Created by Serezha on 2016-11-26.
 */
public class Employee{
    private int cardID;
    private String nameFirst;
    private String nameLast;
    //private enum Sex {MALE, FEMALE} // TODO: 2016-11-26  How to use ENUM in classes?
    private int age;
    //private enum Rank { SUPPORT, TESTER, PROJECTMANAGER, TEAMLEADER, DEVELOPER, SENIOR}
    private double salary;

    public Employee(int cardID, String nameFirst, String nameLast, int age, double salary) {
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

    @Override
    public String toString() {
        return "EmployeeComparator{" +
                "cardID=" + cardID +
                ", nameFirst='" + nameFirst + '\'' +
                ", nameLast='" + nameLast + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}' + "\n";
    }
}
