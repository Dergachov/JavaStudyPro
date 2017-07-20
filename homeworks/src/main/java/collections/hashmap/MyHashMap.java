package collections.hashmap;

/**
 * Created by serezha on 30.05.17.
 */
public class MyHashMap<K, V> {
    private static final int defaultCapacity = 16;
    private double defaultLoadFactor = 0.75;
    private int capacity;
    private int size;
    private Node[] buckets;
    private Node[] reHashBuckets;

    //inner node class
    private static class Node<K, V> {
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
        initialBuckets();
    }

    public MyHashMap(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.defaultLoadFactor = loadFactor;
        initialBuckets();
    }

    private void initialBuckets() {
        buckets = new Node[defaultCapacity];
        this.capacity = defaultCapacity;
        this.size = 0;
    }

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

    private int searchExist(K key, V value) {
        for (int indexBucket = 0; indexBucket < capacity; indexBucket++) {
            if (buckets[indexBucket] == null) {
                continue;
            }
            for (Node<K, V> itr = buckets[indexBucket]; itr != null; itr = itr.next) {
                if (itr.hash == key.hashCode() && (itr.key == key || key.equals(itr.key))) {
                    //This check (value != null) for method GET
                    if (value != null) {
                        if (itr.value == value || value.equals(itr.value)) {
                            return indexBucket;
                        }
                        else {
                            itr.value = value;
                            return indexBucket;
                        }
                    }
                    return indexBucket;
                }
            }
        }
        return -1;
    }

    /**
     * Method for put data to buckets.
     *
     * @param key   data.
     * @param value data.
     * @return boolean
     */
    public boolean put(K key, V value) {
        if (searchExist(key, value) != -1) {
            return true;
        }
        if (size >= capacity * defaultLoadFactor) {
            resize();
        }
        // Create 'Node'
        int indexBucket = key.hashCode() % capacity;
        // Check for issue collision in that bucket
        if (buckets[indexBucket] != null) {
            // Make collision in the end of bucket lis
            for (Node lowerNode = buckets[indexBucket]; lowerNode != null; lowerNode = lowerNode.next) {
                if (lowerNode.next == null) {
                    lowerNode.next = new Node<>(key.hashCode(), key, value, null);
                    ++size;
                    return true;
                }
            }
        } else {
            // Put 'Node' in the empty bucket
            buckets[indexBucket] = new Node<>(key.hashCode(), key, value, null);
            ++size;
            return true;
        }
        return false;
    }

    /**
     * Method for get data from buckets.
     *
     * @param key which for search value data.
     * @return value if exist else return 'null'.
     */
    public V get(K key) {
        if (searchExist(key, null) != -1) {
            for (Node<K, V> itr = buckets[searchExist(key, null)]; itr != null; itr = itr.next) {
                if (itr.hash == key.hashCode() && (itr.key == key || key.equals(itr.key))) {
                    return itr.value;
                }
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return size < 0 ? false : true;
    }

    public int size() {
        return size;
    }
    }
