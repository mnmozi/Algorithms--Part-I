package week2.Assignment;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        private final Item item;
        private Node next;
        private Node prev;

        public Node(Item item) {
            this.item = item;
        }
    }

    private Node start;
    private Node end;
    private int size;

    // construct an empty deque
    public Deque() {
        this.start = null;
        this.end = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;

    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cant add null value!");
        }
        Node newNode = new Node(item);
        if (!isEmpty()) {
            newNode.next = start;
            start.prev = (newNode);
            newNode.next = (start);
        } else {
            end = newNode;
        }
        start = newNode;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cant add null value!");
        }
        Node newNode = new Node(item);
        if (!isEmpty()) {
            end.next = (newNode);
            newNode.prev = (end);
        } else {
            start = newNode;
        }
        end = newNode;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("The Deque is Empty");
        }
        // if only one left
        Node oldStart = start;
        if (size == 1) {
            start = null;
            end = null;
        } else {
            start = start.next;
            start.prev = (null);
        }
        size--;
        return oldStart.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("The Deque is Empty");
        }
        Node oldEnd = end;
        if (size == 1) {
            start = null;
            end = null;
        } else {
            end = end.prev;
            end.next = (null);
        }
        size--;
        return oldEnd.item;

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = start;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current == null) {
                throw new java.util.NoSuchElementException("There is no more elements");
            }
            Item item = current.item;

            current = current.next;

            return item;

        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> list = new Deque<Integer>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        for (Integer element : list) {
            System.out.println(element);
        }
        list.removeFirst();
        list.removeLast();
        System.out.println("----------------------");
        for (Integer element : list) {
            System.out.println(element);
        }

    }

}