package week4;

import java.util.Arrays;
import java.util.Random;

// the max element is the root
public class MaxoPQ<Key extends Comparable<Key>> {

    private Key[] bh;
    private int N;

    public MaxoPQ(int capacity) {
        this.bh = (Key[]) new Comparable[capacity + 1];
    }

    public MaxoPQ() {
        this(1);
    }

    public boolean isEmpty() {
        return N == 0;

    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1))
                j++;
            if (!less(k, j))
                break;
            exch(k, j);
            k = j;
        }
    }

    public void insert(Key k) {
        if (N == bh.length)
            resize(2 * bh.length);
        bh[++N] = k;
        swim(N);
    }

    private Key delMax() {
        Key max = bh[1];
        exch(1, N--);
        sink(1, N);
        bh[N + 1] = null;
        if (N > 0 && N == bh.length / 4)
            resize(bh.length / 2);
        return max;
    }

    public void exch(int i, int j) {
        Key aux = this.bh[i];
        this.bh[i] = bh[j];
        this.bh[j] = aux;

    }

    public boolean less(int i, int j) {
        return (bh[i].compareTo(bh[j])) < 0;
    }

    public void resize(int capacity) {
        Key[] copy = (Key[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = bh[i];
        }
        bh = copy;
    }

    public void heapSort() {
        int nN = N;
        for (int i = nN / 2; i < 1; i--) {
            sink(i, nN);
        }
        while (nN > 1) {
            exch(1, nN--);
            // N--;
            sink(1, nN);
            // in the next line we check if the number is repeated or not
            if (bh[1].compareTo(bh[nN + 1]) == 0) {
                System.out.println(bh[nN + 1]);
            }
        }
    }

    public void printt() {
        for (int i = 0; i < 20; i++) {
            System.out.println(bh[i]);
        }
    }

    public Key[] getHeap() {
        return Arrays.copyOfRange(bh, 1, N + 1);
    }

    public Key sample() {
        Random random = new Random();
        int randomIndex = random.nextInt(N - 1) + 1;
        System.out.println("the random index is: " + randomIndex);
        return bh[randomIndex];
    }

    public Key delRandom() {
        Random random = new Random();
        int randomIndex = random.nextInt(N - 1) + 1;
        Key output = bh[randomIndex];
        exch(randomIndex, N--);
        bh[N + 1] = null;
        if (randomIndex / 2 >= 1) {
            if (bh[randomIndex].compareTo(bh[randomIndex / 2]) < 0) {
                swim(randomIndex);
            } else {
                sink(randomIndex, N);
            }
        } else {
            sink(randomIndex, N);
        }
        return output;
    }

    public static void main(String[] args) {
        MaxoPQ<Integer> x = new MaxoPQ<Integer>(20);
        x.insert(1);
        x.insert(3);
        x.insert(4);
        x.insert(5);
        x.insert(6);
        x.insert(7);
        x.insert(8);

        x.printt();
        x.heapSort();
        x.printt();
    }
}
