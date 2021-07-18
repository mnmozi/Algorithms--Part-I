package week3;

import edu.princeton.cs.algs4.Insertion;

public class MergeSortImprove {
    private static int CUTOFF = 2;

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {

        // here we check if the array length with small size
        if (hi <= lo + CUTOFF - 1) {
            Insertion.sort(aux, lo, hi + 1);
            return;
        }

        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        sort(aux, a, lo, mid);
        sort(aux, a, mid + 1, hi);

        // if the array is sorted dont merge

        merge(a, aux, lo, mid, hi);
    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        for (int i = 0; i < a.length; i++) {
            aux[i] = a[i];
        }
        sort(a, aux, 0, a.length - 1);
        for (int i = 0; i < a.length; i++) {
            System.out.print(aux[i] + " ");
        }
        System.out.print("\n");
    }

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

        assert isSorted(a, lo, mid);
        assert isSorted(a, mid + 1, hi);

        // for (int k = lo; k <= hi; k++) {
        // aux[k] = a[k];
        // }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                aux[k] = a[j++];
            else if (j > hi)
                aux[k] = a[i++];
            else if (less(a[j], a[i]))
                aux[k] = a[j++];
            else
                aux[k] = a[i++];
        }
        assert isSorted(a, lo, hi);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++)
            if (less(a[i], a[i - 1]))
                return false;
        return true;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        Integer[] a = { 82, 54, 14, 23, 90, 9, 7, 5, 2, 1 };
        sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.print("\n");
    }
}