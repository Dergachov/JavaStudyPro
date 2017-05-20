package collections.linkedlist;

import java.util.Iterator;

/**
 * Class MyLinkedList.
 *
 * @version 0.1
 * @since 08.04.2017
 */

/**
 * Class MyLinkedList with generic.
 *
 * @param <T> incoming type.
 */
public class MyLinkedList<T> implements Iterable<T> {

    private Node<T> firstNode;
    private Node<T> lastNode;
    private int size;

    public MyLinkedList() {
        this.size = 0;
    }

    /**
     * Static nested class implements NODE.
     *
     * @param <T> incoming type.
     */
    private static class Node<T> {
        private T element;
        private Node<T> next;
        private Node<T> prev;

        Node(Node<T> prev, T element, Node<T> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }

    private void checkRange(int incomeVar) {
        if (incomeVar < 0 || incomeVar >= size) {
            throw new IndexOutOfBoundsException(" Index: " + incomeVar + ", Size: " + size);
        }
    }

    /**
     * This method for searching Node by index.
     * Returns founded Node object.
     *
     * @param index of searching object
     * @return Node founded object
     */
    private Node<T> findNode(int index) {
        Node<T> node = firstNode;
        for (int i = 0; node.next != null; i++) {
            if (index == i) {
                return node;
            } else {
                node = node.next;
            }
        }
        return node;
    }

    /**
     * This method for create Node and adds it into the end list.
     *
     * @param element incoming value.
     * @return boolean.
     */
    public boolean add(T element) {
        Node<T> newNode = new MyLinkedList.Node<>(lastNode, element, null);
        if (lastNode == null) {
            firstNode = newNode;
        } else {
            lastNode.next = newNode;
        }
        lastNode = newNode;
        ++size;
        return true;
    }

    /**
     * This method for create Node.
     * Adds it into begin or middle and in the end through method add();
     *
     * @param index   after that index adds element into the list.
     * @param element incoming value of adds.
     * @return boolean
     */
    public boolean add(int index, T element) {
        checkRange(index);
        if (index == 0) {
            Node<T> newNode = new MyLinkedList.Node<>(null, element, firstNode);
            firstNode.prev = newNode;
            firstNode = newNode;
            ++size;
        } else if (index != size - 1) {
            Node<T> newNode = new MyLinkedList.Node<>(findNode(index).prev, element, findNode(index));
            findNode(index).prev.next = newNode;
            findNode(index).prev = newNode;
            ++size;
        } else {
            add(element);
        }
        return true;
    }

    public T get(int index) {
        checkRange(index);
        return findNode(index).element;
    }

    /**
     * This method for set value into Node by index
     * and returns the value of which was before changed.
     *
     * @param index   into that index will be adds.
     * @param element incoming value of set.
     * @return the value of which was before changed.
     */
    public T set(int index, T element) {
        checkRange(index);
        T temp = findNode(index).element;
        findNode(index).element = element;
        return temp;
    }

    /**
     * Method objects contains.
     *
     * @param element for equals
     * @return boolean.
     */
    public boolean contains(T element) {
        Iterator<T> itr = iterator();
        while (itr.hasNext()) {
            if (itr.next().equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method for remove Node by index
     * and returns the value of which will be removed.
     *
     * @param index that index will be removed.
     * @return the value of which will be removed.
     */
    public T remove(int index) {
        checkRange(index);
        Node<T> removeNode = findNode(index);
        T removeElement = removeNode.element;
        // remove node in the end
        if (index == size - 1) {
            // remove last node
            if (size == 1) {
                firstNode = null;
                lastNode = null;
                --size;
                return removeElement;
            }
            lastNode = removeNode.prev;
            removeNode.prev.next = null;
            --size;
            return removeElement;
        }
        // remove node in the middle
        if (index > 0) {
            removeNode.prev.next = removeNode.next;
            removeNode.next.prev = removeNode.prev;
            --size;
            return removeElement;
        }
        // remove node in the begin
        firstNode = firstNode.next;
        firstNode.prev = null;
        --size;
        return removeElement;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    /**
     * Inner class for implements LinkedListIterator.
     */
    private class LinkedListIterator implements Iterator<T> {
        private Node<T> nodeItr;

        LinkedListIterator() {
            this.nodeItr = firstNode;
        }

        @Override
        public boolean hasNext() {
            if (nodeItr != null) {
                if (nodeItr.next != null) {
                    return true;
                }
                if (nodeItr.prev.next == nodeItr) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public T next() {
            T item = nodeItr.element;
            nodeItr = nodeItr.next;
            return item;
        }
    }
}
