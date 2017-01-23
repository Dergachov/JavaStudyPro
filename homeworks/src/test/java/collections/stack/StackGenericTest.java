package collections.stack;

import org.junit.Before;
import org.junit.Test;
import overall.Employee;

import java.util.Iterator;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class StackGenericTest {

    private StackGeneric<Employee> stackNoSize = new StackGeneric<>();
    private StackGeneric<Employee> stackWithSize = new StackGeneric<>(5);

    Employee em1 = new Employee(123789456, "Peter", "Jackson", 30, 5001);
    Employee em2 = new Employee(321789456, "Dora", "Norris", 25, 2000);
    Employee em3 = new Employee(123456987, "Peter", "O’Connor", 24, 8000);
    Employee em4 = new Employee(123654789, "Mark", "Jefferson", 22, 8000);
    Employee em5 = new Employee(123987654, "Mark", "McCoy", 35, 10000);
    Employee em6 = new Employee(987654321, "Robert", "Hall", 26, 6000);
    Employee em7 = new Employee(251287888, "Oliver", "Pitts", 33, 6000);
    Employee em8 = new Employee(891313545, "Ginger", "Edwards", 27, 1000);
    Employee em9 = new Employee(879117868, "Christine", "Rice", 28, 1000);
    Employee em10 = new Employee(238888789, "Juliet", "Manning", 22, 1000);

    @Before
    public void setUp() throws Exception {

    }

    @org.junit.Test
    public void testPush() throws Exception {
        System.out.println("Start test stackNoSize");
        System.out.println("Stack size  " + stackNoSize.getStackSize());
        stackNoSize.push(em1);
        stackNoSize.push(em2);
        stackNoSize.push(em3);
        stackNoSize.push(em4);
        stackNoSize.push(em5);
        stackNoSize.push(em6);
        stackNoSize.push(em7);
        stackNoSize.push(em8);
        stackNoSize.push(em9);
        stackNoSize.push(em10);
        System.out.println("Stack size  " + stackNoSize.getStackSize());
        System.out.println("End test stackNoSize");

        System.out.println("Start test stackWithSize");
        System.out.println("Stack WITH size  " + stackWithSize.getStackSize());
        stackWithSize.push(em1);
        stackWithSize.push(em2);
        stackWithSize.push(em3);
        stackWithSize.push(em4);
        stackWithSize.push(em5);
        stackWithSize.push(em6);
        stackWithSize.push(em7);
        stackWithSize.push(em8);
        stackWithSize.push(em9);
        stackWithSize.push(em10);
        System.out.println("Stack WITH size  " + stackWithSize.getStackSize());
        System.out.println("End test stackWithSize");
    }

    @Test
    public void testPop() throws Exception {
        stackNoSize.push(em1);
        stackNoSize.push(em2);
        stackNoSize.push(em3);
        stackNoSize.push(em4);
        stackNoSize.push(em5);
        stackNoSize.push(em6);
        stackNoSize.push(em7);
        stackNoSize.push(em8);
        stackNoSize.push(em9);
        stackNoSize.push(em10);

        Iterator<Employee> iterator = stackNoSize.iterator();
        while (iterator.hasNext()) {
            Employee cur = iterator.next();
            assertTrue("Bad CardID", cur.getCardID() != 0);
            assertFalse("Bad Salary", cur.getSalary() < 0);
        }
    }
}