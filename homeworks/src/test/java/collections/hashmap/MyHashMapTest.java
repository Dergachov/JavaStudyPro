package collections.hashmap;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by serezha on 21.07.17.
 */
public class MyHashMapTest {
    private Map<Integer, String> map = new HashMap<>();
    private int numberOfAdds = 80;
    private String valueOfAdds = "String";

    @Before
    public void before() {
        for (int key = 0; key < numberOfAdds; key++) {
            map.put(key, valueOfAdds + key);
        }
    }

    @Test
    public void putWithoutExistKeyTest() throws Exception {
        int keyPut = 1000;
        String valuePut = "Test string with key 1000";
        assertEquals(map.put(keyPut, valuePut), null);

        //Should be change size.
        int currentSize = map.size();
        int numberAddsElements = 1;
        assertEquals(currentSize, numberOfAdds + numberAddsElements);
    }

    @Test
    public void putWithExistKeyTest() throws Exception {
        int keyPut = 1;
        String valuePut = "Test string with key 1";
        String expectedValue = "String1";
        assertEquals(map.put(keyPut, valuePut), expectedValue);

        //Should be no change size.
        int currentSize = map.size();
        assertEquals(currentSize, numberOfAdds);
    }

    @Test
    public void getTest() throws Exception {
        int keyGet = 10;
        String expectedGetValue = "String10";
        assertEquals(map.get(keyGet), expectedGetValue);
    }

    @Test
    public void removeTest() throws Exception {
        int keyRemove = 0;
        String expectedRemoveValue = "String0";
        assertEquals(map.remove(keyRemove), expectedRemoveValue);
    }

    @Test
    public void containsKeyTrueTest() throws Exception {
        int keyContains = 20;
        assertTrue(map.containsKey(keyContains));
    }

    @Test
    public void containsKeyFalseTest() throws Exception {
        int keyContains = 100;
        assertFalse(map.containsKey(keyContains));
    }

    @Test
    public void containsValueTrueTest() throws Exception {
        String valueContains = "String0";
        assertTrue(map.containsValue(valueContains));
    }

    @Test
    public void containsValueFalseTest() throws Exception {
        String valueContains = "null";
        assertFalse(map.containsValue(valueContains));
    }

    @Test
    public void putAllTest() throws Exception {
        int keyExpectedValue = 0;
        String expectedValue = "Changed value with key 0";

        HashMap<Integer, String> map2 = new HashMap<>();
        map2.put(keyExpectedValue, expectedValue);
        map.putAll(map2);

        assertEquals(map.get(keyExpectedValue), expectedValue);
    }

    @Test
    public void collectionValuesTest() throws Exception {
        Collection<String> values = map.values();
        Iterator<String> iterator = values.iterator();

        while (iterator.hasNext()) {
            String next = iterator.next();
            assertTrue(map.containsValue(next));
            iterator.remove();
        }
    }

    @Test
    public void entrySetTest() throws Exception {
        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        Iterator<Map.Entry<Integer, String>> iterator = entries.iterator();

        while (iterator.hasNext()) {
            Map.Entry<Integer, String> next = iterator.next();
            assertTrue(map.containsKey(next.getKey()));
            assertTrue(map.containsValue(next.getValue()));
            iterator.remove();
        }
    }

    @Test
    public void keySetTest() throws Exception {
        Set<Integer> entries = map.keySet();
        Iterator<Integer> iterator = entries.iterator();

        for (int numberKey = 0; iterator.hasNext(); numberKey++) {
            int next = iterator.next();
            assertEquals(next, numberKey);
        }
    }

    @Test
    public void isEmptyTest() throws Exception {
        assertFalse(map.isEmpty());
    }

    @Test
    public void clearTest() throws Exception {
        map.clear();
        assertTrue(map.isEmpty());
    }

    @Test
    public void sizeTest() throws Exception {
        int currentSize = map.size();
        assertEquals(currentSize, numberOfAdds);
    }

}