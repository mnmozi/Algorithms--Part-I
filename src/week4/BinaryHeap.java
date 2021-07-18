package week4;

import java.util.Arrays;

public class BinaryHeap<Key extends Comparable<Key>> {

    private Key[] bh;
    private int N;

    public BinaryHeap(int capacity) {
        this.bh = (Key[]) new Comparable[capacity + 1];
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

    public void insert(Key k) {
        bh[++N] = k;
        swim(N);
    }

    public void exch(int i, int j) {
        Key aux = this.bh[i];
        this.bh[i] = bh[j];
        this.bh[j] = aux;

    }

    private void sink(int k) {
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

    private Key delMax() {
        Key max = bh[N];
        exch(1, N--);
        sink(1);
        bh[N + 1] = null;
        return max;
    }

    public boolean less(int i, int j) {
        return (bh[i].compareTo(bh[j])) < 0;
    }

    private void heapSort() {
        int nN = N;
        for (int i = nN / 2; i < 1; i--) {
            sink(i);
        }
        while (nN > 1) {
            exch(1, nN--);
            N--;
            sink(1);
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

    public static void main(String[] args) {
        BinaryHeap<Integer> x = new BinaryHeap<Integer>(20);
        x.insert(1);
        x.insert(3);
        x.insert(4);
        x.insert(5);
        x.insert(6);
        x.insert(7);
        x.insert(8);

        x.printt();
        // x.heapSort();
        // x.printt();
    }
}
