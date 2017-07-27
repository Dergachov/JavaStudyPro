package collections.hashmap;

import java.lang.reflect.Array;
import java.util.*;

/**
 * MyHashMap class.
 *
 * @param <K> type of key.
 * @param <V> type of value.
 */
public class MyHashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    private int capacity;
    private int size;
    private Node[] buckets;

    /**
     * Class 'Node' for storage objects in the buckets.
     *
     * @param <K> type of key.
     * @param <V> type of value.
     */
    static class Node<K, V> {
        private final int hash;
        private final K key;
        private V value;
        private Node next;

        Node(int hashCode, K key, V value, Node next) {
            this.hash = hashCode;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public MyHashMap() {
        initial();
    }

    /**
     * Method for default initial array of buckets.
     */
    private void initial() {
        buckets = new Node[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
        this.size = 0;
    }

    private void checkForNull(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
    }

    /**
     * Method for calc threshold.
     *
     * @return threshold.
     */
    private int threshold() {
        return (int) (this.capacity * DEFAULT_LOAD_FACTOR);
    }

    /**
     * Method for increase array of buckets.
     */
    private void resize() {
        Node[] reHashBuckets = new Node[capacity + threshold()];
        for (int indexBucket = 0; indexBucket < capacity; indexBucket++) {
            if (buckets[indexBucket] == null) {
                continue;
            }
            rehash(reHashBuckets, buckets[indexBucket]);
            if (buckets[indexBucket].next != null) {
                for (Node lowerNode = buckets[indexBucket].next; lowerNode != null; lowerNode = lowerNode.next) {
                    rehash(reHashBuckets, lowerNode);
                }
            }
        }
        capacity += threshold();
        buckets = new Node[capacity];
        System.arraycopy(reHashBuckets, 0, buckets, 0, buckets.length);
    }

    /**
     * Method for rehash Nodes.
     *
     * @param reHashBuckets incoming temp array of Nodes with new rehash objects.
     * @param rehashNode    incoming Node for rehash.
     */
    private void rehash(Node[] reHashBuckets, Node rehashNode) {
        int reHashCapacity = capacity + threshold();
        int numberBucket = rehashNode.key.hashCode() & (reHashCapacity - 1);
        if (reHashBuckets[numberBucket] == null) {
            reHashBuckets[numberBucket] = new Node<>(rehashNode.hash, rehashNode.key, rehashNode.value, null);
        } else {
            for (Node lowerNode = reHashBuckets[numberBucket]; lowerNode != null; lowerNode = lowerNode.next) {
                if (lowerNode.next == null) {
                    lowerNode.next = new Node<>(rehashNode.hash, rehashNode.key, rehashNode.value, null);
                    break;
                }
            }
        }
    }

    /**
     * Method for get data from buckets.
     *
     * @param key which for search value data.
     * @return object if exist else return 'null'.
     */
    @Override
    public V get(Object key) {
        int indexBucket = key.hashCode() & (capacity - 1);
        for (Node<K, V> itr = buckets[indexBucket]; itr != null; itr = itr.next) {
            if (itr.hash == key.hashCode() && (itr.key == key || key.equals(itr.key))) {
                return itr.value;
            }
        }
        return null;
    }

    /**
     * Method for put objects to buckets.
     *
     * @param key   object.
     * @param value object.
     * @return 'null' or object if incoming key object exist in the buckets.
     */
    @Override
    public V put(K key, V value) {
        if (size >= threshold()) {
            resize();
        }
        // Create 'Node'
        int indexBucket = key.hashCode() & (capacity - 1);
        // Check for issue collision in that bucket.
        if (buckets[indexBucket] != null) {
            // Make collision in the end of bucket list.
            for (Node<K, V> lowerNode = buckets[indexBucket]; lowerNode != null; lowerNode = lowerNode.next) {
                // Check exist 'key' object and return an old object 'value'.
                if (lowerNode.hash == key.hashCode() && (lowerNode.key == key || key.equals(lowerNode.key))) {
                    V oldValue = lowerNode.value;
                    lowerNode.value = value;
                    return oldValue;
                }
                // If not exist 'key' object in the buckets.
                if (lowerNode.next == null) {
                    lowerNode.next = new Node<>(key.hashCode(), key, value, null);
                    ++size;
                    return null;
                }
            }
        } else {
            // Put 'Node' object in the empty bucket
            buckets[indexBucket] = new Node<>(key.hashCode(), key, value, null);
            ++size;
            return null;
        }
        return null;
    }

    /**
     * Remove method.
     *
     * @param key is value which will be delete.
     * @return deleting object
     */
    @Override
    public V remove(Object key) {
        Node previous = null;
        int indexBucket = key.hashCode() & (capacity - 1);
        for (Node<K, V> iterator = buckets[indexBucket]; iterator != null; iterator = iterator.next) {
            if (iterator.hash == key.hashCode() && (iterator.key == key || key.equals(iterator.key))) {
                --size;
                //For begin of collision
                if (previous == null) {
                    buckets[indexBucket] = iterator.next;
                    return iterator.value;
                }
                //For middle and end of collision
                if (iterator.next != null) {
                    previous.next = iterator.next;
                    return iterator.value;
                } else {
                    previous.next = null;
                    return iterator.value;
                }
            }
            previous = iterator;
        }
        return null;
    }

    @Override
    public void clear() {
        if (buckets != null && size > 0) {
            for (int indexBucket = 0; indexBucket < buckets.length; ++indexBucket) {
                buckets[indexBucket] = null;
            }
            size = 0;
        }
    }

    @Override
    public boolean containsKey(Object key) {
        checkForNull(key);
        if (isEmpty()) {
            return false;
        }
        int indexBucket = key.hashCode() & (capacity - 1);
        for (Node itr = buckets[indexBucket]; itr != null; itr = itr.next) {
            if (itr.hash == key.hashCode() && (itr.key == key || key.equals(itr.key))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        checkForNull(value);
        if (isEmpty()) {
            return false;
        }
        for (int indexBucket = 0; indexBucket < buckets.length; ++indexBucket) {
            if (buckets[indexBucket] == null) {
                continue;
            }
            if (buckets[indexBucket].value == value || buckets[indexBucket].value.equals(value)) {
                return true;
            }
            if (buckets[indexBucket].next != null) {
                for (Node lowerNode = buckets[indexBucket].next; lowerNode != null; lowerNode = lowerNode.next) {
                    if (lowerNode.value.equals(value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    public int size() {
        return this.size;
    }

    /*

        There are while not implemented methods.

     */

    // Need implements class with extends abstract class AbstractCollection<V>
    @Override
    public Collection<V> values() {
        return null;
    }

    // Need implements class with extends abstract class AbstractSet<K>
    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public void putAll(Map map) {
    }

    @Override
    public boolean equals(Object o) {
        return o.hashCode() > 0;
    }

    @Override
    public int hashCode() {
        return 0;
    }

}
