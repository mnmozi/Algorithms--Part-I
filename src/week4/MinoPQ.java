package week4;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

// its is a replica of the class edu.princeton.cs.algs4.MinPQ
// just practicing  
public class MinoPQ<Key extends Comparable<Key>> {

    private Key[] pq;
    private int n;
    private Comparator<Key> comparator;

    /**
     * Initializes an empty priority queue with the given initial capacity.
     *
     * @param initCapacity the initial capacity of this priority queue
     */
    public MinoPQ(int initCapacity) {
        this.pq = (Key[]) new Object[initCapacity + 1];
        this.n = 0;
    }

    /**
     * Initializes an empty priority queue.
     */
    public MinoPQ() {
        this(1);
    }

    /**
     * Initializes an empty priority queue with the given initial capacity, using
     * the given comparator.
     * 
     * @param initCapacity the initial capacity of the priority queue
     * @param comparator   the order in which we compare the keys
     */
    public MinoPQ(int initCapacity, Comparator<Key> comparator) {
        this.pq = (Key[]) new Object[initCapacity + 1];
        this.n = 0;
        this.comparator = comparator;
    }

    /**
     * 
     * @param keys The arrays of keys that we want to make a priority queue of it
     */
    public MinoPQ(Key[] keys) {
        this.pq = (Key[]) new Object[keys.length + 1];
        this.n = keys.length + 1;
        for (int i = 0; i < keys.length; i++) {
            pq[i + 1] = keys[i];
        }
        for (int i = n / 2; i >= 1; i--) {
            sink(i);
        }
        assert isMinHeap();
    }

    /**
     * 
     * @return {@code true} if the priority queue is empty {@code false} otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of keys in the priority queue.
     * 
     * @return the number of keys in the priority queue
     */
    public int size() {
        return n;
    }

    /**
     * Returns the smallest key in the priority queue
     * 
     * @return the smallest Key in the priority queue
     * @throws NoSuchElementException if the array is empty
     */
    public Key min() {
        if (isEmpty())
            throw new NoSuchElementException("Priority queue is underflow");
        return pq[1];
    }

    /**
     * Add a new key to the priority queue
     * 
     * @param newKey the new key to add to the queue
     */
    public void insert(Key newKey) {
        // resize the array if nescessary
        if (n == pq.length - 1)
            resize(2 * pq.length);

        // add newKey, and percolate it to maintain the heap invariant
        pq[++n] = newKey;
        swim(n);

        assert isMinHeap();
    }

    public Key delMin() {
        if (isEmpty())
            throw new NoSuchElementException("Priority queue underflow");
        Key min = pq[1];
        swap(1, n--);
        sink(1);
        pq[n + 1] = null; // to avoid loitering and help with garbage collection
        if (n > 0 && n == (pq.length / 4))
            resize(pq.length / 2);
        assert isMinHeap();
        return min;
    }

    private void resize(int capacity) {
        assert capacity > n;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    /***************************************************
     * Helper functions
     ***************************************************/
    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j + 1, j))
                j++;
            if (!greater(j, k))
                break;
            swap(k, j);
            k = k / 2;
        }
    }

    /***************************************************
     * Helper functions to swap and compare
     ***************************************************/

    private boolean greater(int key1, int key2) {
        if (this.comparator == null) {
            return pq[key1].compareTo(pq[key2]) > 0;
        } else {
            return comparator.compare(pq[key1], pq[key2]) > 0;
        }
    }

    private void swap(int key1, int key2) {
        Key temp = pq[key1];
        pq[key1] = pq[key2];
        pq[key2] = temp;
    }

    private boolean isMinHeap() {
        for (int i = 1; i <= n; i++) {
            if (pq[i] == null)
                return false;
        }
        for (int i = n + 1; i < pq.length; i++) {
            if (pq[i] != null)
                return false;
        }
        if (pq[0] != null)
            return false;
        return isMinHeapOrdered(1);
    }

    private boolean isMinHeapOrdered(int k) {
        if (k > n)
            return true;
        int left = k * 2;
        int right = left + 1;
        if (left <= n && greater(k, left))
            return false;
        if (right <= n && greater(k, right))
            return false;
        return (isMinHeapOrdered(left) && isMinHeapOrdered(right));
    }

    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {
        private MinoPQ<Key> copy;

        // add all items to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            if (comparator == null)
                copy = new MinoPQ<>(size());
            else
                copy = new MinoPQ<>(size(), comparator);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i]);
        }

        public boolean hasNext() {
            if (copy.isEmpty())
                return true;
            return false;
        }

        public Key next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return copy.delMin();
        }

    }

}
