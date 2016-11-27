package com.cbs.edu.collections.my_hash_map;

public class HashMap<K, V> {

    private Node<K, V>[] buckets;
    private int capacity;
    private float loadFactor;
    private final int DEFAULT_CAPACITY = 16;
    private final float DEFAULT_LOAD_FACTOR = 0.75f;
    private int size = 0;

    public HashMap() {
        buckets = (Node<K, V>[])new Node[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
        loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public HashMap(int capacity) {
        buckets = (Node<K, V>[])new Node[capacity];
        this.capacity = capacity;
        loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public HashMap(int capacity, float loadFactor) {
        buckets = (Node<K, V>[])new Node[capacity];
        this.capacity = capacity;
        this.loadFactor = loadFactor;
    }

    public void put(K key, V value) {
        if (checkCapacity()) ensureCapacity();
        final int num = getBucket(key);
        Node<K, V> ins = new Node<>(key, value);
        if (buckets[num] == null) {
            buckets[num] = ins;
        } else {
            Node<K, V> tmp = buckets[num];
            buckets[num] = ins;
            buckets[num].next = tmp.next;
        }
    }

    public V get(K key) {
        final int num = getBucket(key);
        Node<K, V> tmp = buckets[num];
        while (true) {

        }
    }

    private void ensureCapacity() {

    }

    private int getBucket(K key) {
        return key.hashCode() % capacity;
    }

    private boolean checkCapacity() {
        return size > buckets.length * loadFactor;
    }

    private class Node<K, V> {
        final K key;
        V value;
        final int hash;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.hash = key.hashCode();
        }
    }


}
