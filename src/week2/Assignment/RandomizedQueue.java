package week2.Assignment;

import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.queue = (Item[]) new Object[2];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return (size == 0);
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Can't add a null");
        }
        if (size == queue.length) {
            resize(2 * queue.length);
        }
        queue[size++] = item;

    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Queue is Empty!");
        }
        int randomNumber = StdRandom.uniform(size);
        Item item = queue[randomNumber];
        if (randomNumber != size - 1) {
            queue[randomNumber] = queue[size - 1];
        }
        queue[size - 1] = null;
        size--;
        if (size > 0 && size == queue.length / 4)
            resize(queue.length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Queue is Empty!");
        }
        int randomNumber = StdRandom.uniform(size);
        Item item = queue[randomNumber];
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RQIterator();
    }

    private class RQIterator implements Iterator<Item> {
        private Item[] queueCopy = (Item[]) new Object[queue.length];

        private int sizeCopy;

        public RQIterator() {
            sizeCopy = size;
            for (int i = 0; i < size; i++) {
                queueCopy[i] = queue[i];
            }

        }

        public boolean hasNext() {
            return sizeCopy != 0;
        }

        public Item next() {
            if (sizeCopy == 0) {
                throw new java.util.NoSuchElementException("There is no more Elements!");
            }
            int randomNumber = StdRandom.uniform(sizeCopy);
            Item item = queueCopy[randomNumber];
            if (randomNumber != sizeCopy - 1) {
                queueCopy[randomNumber] = queueCopy[sizeCopy - 1];
            }
            queueCopy[size - 1] = null;
            sizeCopy--;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = queue[i];
        }
        queue = copy;
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        for (Integer i : queue) {
            System.out.println(i);
        }
        queue.dequeue();
        queue.dequeue();
        System.out.println(queue.sample());
    }

}
