package com.cbs.edu.collections.my_link_list;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {

    private int size;
    private Node<T> header;

    public LinkedList() {
        header = new Node<>(null, header, header);
    }

    public int size() {
        return size;
    }

    public void add(T elem) {
        Node<T> node;
        ++size;
        if (size == 1) {
            node = new Node<>(elem, header, header);
            header.next = node;
            header.prev = node;
        } else {
            node = new Node<>(elem, header.prev, header);
            node.prev.next = node;
            header.prev = node;
        }
    }

    public void add(int index, T elem) {
        Node<T> tmp = getNode(index);
        Node<T> ins = new Node<>(elem, tmp.prev, tmp);
        tmp.prev.next = ins;
        tmp.prev = ins;
        size++;
    }

    public boolean contains(T elem) {
        NodeItr nodeItr = new NodeItr();
        while (nodeItr.hasNext()) {
            if (nodeItr.next().value.equals(elem)) return true;
        }
        return false;
    }

    public T get(int index) {
        return getNode(index).value;
    }

    private Node<T> getNode(int index) {
        if (0 > index || index > size - 1) throw new IllegalArgumentException();
        if (index == 0) return header.next;
        if (index == size - 1) return header.prev;
        NodeItr itr = new NodeItr();
        Node<T> elem = null;
        for (int i = 0; i <= index; i++) {
            elem = itr.next();
        }
        return elem;
    }

    public void set(int index, T value) {
        getNode(index).value = value;
    }

    public void remove(int index) {
        Node<T> tmp = getNode(index);
        tmp.prev.next = tmp.next;
        tmp.next.prev = tmp.prev;
        tmp.prev = null;
        tmp.next = null;
        size--;
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        private NodeItr nodeItr = new NodeItr();

        @Override
        public boolean hasNext() {
            return nodeItr.hasNext();
        }

        @Override
        public T next() {
            return nodeItr.next().value;
        }

        @Override
        public void remove() {
            nodeItr.remove();
        }
    }

    private class NodeItr implements Iterator<Node<T>> {
        private Node<T> cur = header.next;
        private int count = 0;

        @Override
        public boolean hasNext() {
            return count++ < size;
        }

        @Override
        public Node<T> next() {
            Node<T> elem = cur;
            cur = cur.next;
            return elem;
        }

        @Override
        public void remove() {
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
            cur.prev = null;
            cur.next = null;
        }
    }

    private static class Node<T> {
        T value;
        Node<T> next;
        Node<T> prev;

        public Node(T value, Node<T> prev, Node<T> next) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
}
