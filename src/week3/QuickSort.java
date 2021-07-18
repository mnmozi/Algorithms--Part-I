package week3;

import java.util.Random;

public class QuickSort {

    public static void shuffle(Object[] array) {
        Random random = new Random();
        int n = array.length;
        for (int i = 0; i < n; i++) {
            // pick random between i and array.length
            int j = i + random.nextInt(n - i);
            Object tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    public static void sort(Comparable[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo)
            return;
        int mid = partition(a, lo, hi);
        sort(a, lo, mid - 1);
        sort(a, mid + 1, hi);

    }

    public static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;

        while (true) {

            while (a[lo].compareTo(a[++i]) == 1) {
                if (i == hi)
                    break;
            }
            while (a[lo].compareTo(a[--j]) == -1) {
                if (j == lo)
                    break;
            }
            if (i >= j)
                break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable x = a[i];
        a[i] = a[j];
        a[j] = x;
    }

    public static void main(String[] args) {
        Comparable x[] = { 1, 2, 3, 4, 5, 6 };
        sort(x);
        for (Comparable comparable : x) {
            System.out.println(comparable);
        }
    }
}
