package collections.hashmap;

/**
 * Created by serezha on 30.05.17.
 */

/**
 * MyHashMap class.
 *
 * @param <K> key.
 * @param <V> value.
 */
public class MyHashMap<K, V> {
    private final int defaultCapacity;
    private final double defaultLoadFactor;
    private int capacity;
    private int size;
    private Node[] buckets;
    private Node[] reHashBuckets;

    /**
     * This is inner class Node with generics.
     *
     * @param <K> key.
     * @param <V> value.
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
        this.defaultCapacity = 16;
        this.defaultLoadFactor = 0.75;
        initial();
    }

    public MyHashMap(int capacity, double loadFactor) {
        this.defaultCapacity = capacity;
        this.defaultLoadFactor = loadFactor;
        initial();
    }

    /**
     * This is method for initial array of buckets.
     */
    private void initial() {
        if (defaultCapacity > 0) {
            buckets = new Node[defaultCapacity];
            this.capacity = defaultCapacity;
            this.size = 0;
        } else {
            throw new IndexOutOfBoundsException("Bad capacity: " + this.capacity);
        }
    }

    /**
     * This is method for increase array of buckets.
     */
    private void resize() {
        reHashBuckets = new Node[capacity + (int) (capacity * defaultLoadFactor)];
        for (int indexBucket = 0; indexBucket < capacity; indexBucket++) {
            if (buckets[indexBucket] == null) {
                continue;
            }
            rehash(buckets[indexBucket]);
            if (buckets[indexBucket].next != null) {
                for (Node lowerNode = buckets[indexBucket].next; lowerNode != null; lowerNode = lowerNode.next) {
                    rehash(lowerNode);
                }
            }
        }
        capacity += (int) (capacity * defaultLoadFactor);
        buckets = new Node[capacity];
        buckets = reHashBuckets;
        reHashBuckets = null;
    }

    /**
     * This method for rehash Nodes.
     *
     * @param node incoming for rehashing.
     */
    private void rehash(Node node) {
        int reHashCapacity = capacity + (int) (capacity * defaultLoadFactor);
        int numberBucket = node.key.hashCode() % reHashCapacity;
        if (reHashBuckets[numberBucket] == null) {
            reHashBuckets[numberBucket] = new Node<>(node.hash, node.key, node.value, null);
        } else {
            for (Node lowerNode = reHashBuckets[numberBucket]; lowerNode != null; lowerNode = lowerNode.next) {
                if (lowerNode.next == null) {
                    lowerNode.next = new Node<>(node.hash, node.key, node.value, null);
                    break;
                }
            }
        }
    }

    /**
     * Method for put data to buckets.
     *
     * @param key   data.
     * @param value data.
     * @return null or value if incoming key exist in the buckets.
     */
    public V put(K key, V value) {
        if (size >= capacity * defaultLoadFactor) {
            resize();
        }
        // Create 'Node'
        int indexBucket = key.hashCode() % capacity;
        // Check for issue collision in that bucket
        if (buckets[indexBucket] != null) {
            // Make collision in the end of bucket lis
            for (Node<K, V> lowerNode = buckets[indexBucket]; lowerNode != null; lowerNode = lowerNode.next) {
                // Check exist key an return old value
                if (lowerNode.hash == key.hashCode() && (lowerNode.key == key || key.equals(lowerNode.key))) {
                    V oldValue = lowerNode.value;
                    lowerNode.value = value;
                    return oldValue;
                }
                // if not exist key in buckets
                if (lowerNode.next == null) {
                    lowerNode.next = new Node<>(key.hashCode(), key, value, null);
                    ++size;
                    return null;
                }
            }
        } else {
            // Put 'Node' in the empty bucket
            buckets[indexBucket] = new Node<>(key.hashCode(), key, value, null);
            ++size;
            return null;
        }
        return null;
    }

    /**
     * Method for get data from buckets.
     *
     * @param key which for search value data.
     * @return value if exist else return 'null'.
     */
    public V get(K key) {
        for (Node<K, V> itr = buckets[key.hashCode() % capacity]; itr != null; itr = itr.next) {
            if (itr.hash == key.hashCode() && (itr.key == key || key.equals(itr.key))) {
                return itr.value;
            }
        }
        return null;
    }

    /**
     * This is remove methods.
     *
     * @param key is value which will be delete.
     * @return deleted value
     */
    public V remove(K key) {
        Node<K, V> previous = null;
        int indexBucket = key.hashCode() % capacity;
        for (Node<K, V> iterator = buckets[indexBucket]; iterator != null; iterator = iterator.next) {
            if (iterator.hash == key.hashCode() && (iterator.key == key || key.equals(iterator.key))) {
                //For begin of collision
                if (previous == null) {
                    buckets[indexBucket] = iterator.next;
                    --size;
                    return iterator.value;
                }
                //For middle and end of collision
                if (iterator.next != null) {
                    previous.next = iterator.next;
                    --size;
                    return iterator.value;
                } else {
                    previous.next = null;
                    --size;
                    return iterator.value;
                }
            }
            previous = iterator;
        }
        return null;
    }

    public boolean isEmpty() {
        return size >= 0 ? false : true;
    }

    public void clear() {
        initial();
    }

    public int size() {
        return size;
    }
}
