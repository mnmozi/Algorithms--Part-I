package week3.InterviewQuestions;

import java.util.Random;

public class findK {
    // find the kth element in array
    public static void shuffle(Object[] a) {
        Random random = new Random();
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int randIndex = i + random.nextInt(n - i);
            Object tmp = a[i];
            a[i] = a[randIndex];
            a[randIndex] = tmp;
        }
    }

    public static Object findK(Comparable[] a, int k) {
        shuffle(a);
        return find(a, 0, a.length, k);
    }

    public static Object find(Comparable[] a, int lo, int hi, int k) {
        if (lo >= hi) {
            return null;
        }
        int v = partition(a, lo, hi - 1, k);
        if (v != -1) {

            return a[v];
        }
        return null;
    }

    public static int partition(Comparable[] a, int lo, int hi, int k) {
        int i = lo;
        int lt = lo;
        int gt = hi;
        if (i > gt) {
            return -1;
        }
        while (i <= gt) {
            if (a[lt].compareTo(a[i]) > 0) {
                exch(a, lt++, i++);
            } else if (a[lt].compareTo(a[i]) < 0) {
                exch(a, i, gt--);
            } else if (a[lt].compareTo(a[i]) == 0) {
                i++;
            }
        }
        if (k < lt) {
            return partition(a, lo, lt - 1, k);

        } else if (k > lt) {
            return partition(a, lt + 1, hi, k);

        } else
            return lt;

    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable x = a[i];
        a[i] = a[j];
        a[j] = x;
    }

    public static void main(String[] args) {
        Comparable x[] = { 1, 2, 3, 4, 5, 6, 3 };
        System.out.println(findK(x, 3));

    }
}
