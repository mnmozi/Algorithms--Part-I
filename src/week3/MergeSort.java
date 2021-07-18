package week3;

import edu.princeton.cs.algs4.MergeX;

public class MergeSort {
    public static int CUTOFF = 2;

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {

        // here we check if the array length with small size
        if (hi <= lo + CUTOFF) {
            insertionSort(aux, lo, hi);
            return;
        }

        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        sort(aux, a, lo, mid);
        sort(aux, a, mid + 1, hi);

        // if the array is sorted dont merge
        if (!less(aux[mid + 1], aux[mid]))
            return;
        merge(a, aux, lo, mid, hi);
    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = a.clone();
        sort(aux, a, 0, a.length - 1);
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

    public static void insertionSort(Comparable[] a, int lo, int hi) {
        if (hi > a.length) {
            hi = a.length - 1;
        }
        for (int i = lo; i <= hi; i++) {
            for (int j = i + 1; j > lo && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable x = a[i];
        a[i] = a[j];
        a[j] = x;
    }

    public static void main(String[] args) {
        Integer[] a = { 1, 8, 5, 4, 3 };
        MergeX.sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}