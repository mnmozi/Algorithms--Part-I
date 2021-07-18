package week3.InterviewQuestions;

import java.util.ArrayList;
import java.util.Random;

public class InterviewQuickSort {
    // Question 1
    // modify the quick sort to sort both ... since they contain the same data

    // Question 2 Selection in two sorted arrays.
    public static Comparable selection(Comparable[] a1, Comparable[] a2, int k) {
        if (k > a1.length + a2.length) {
            throw new IllegalArgumentException("K is out of boundaries");
        }
        int lo1 = 0;
        int hi1 = a1.length - 1;
        int mid1 = lo1 + (hi1 - lo1) / 2;

        int lo2 = 0;
        int hi2 = a2.length - 1;
        int mid2 = lo2 + (hi2 - lo2) / 2;
        while (true) {
            int totalElements = mid1 + 1 + mid2 + 1;
            if (totalElements > k) {
                if (a1[mid1].compareTo(a2[mid2]) > 0) {
                    mid1 = lo1 + (((mid1 - 1) - lo1) / 2);
                } else {
                    mid2 = lo2 + (((mid2 - 1) - lo2) / 2);
                }
            } else if (totalElements < k) {
                if (a1[mid1].compareTo(a2[mid2]) > 0) {
                    mid2 = (mid2 + 1) + ((hi2 - (mid2 + 1)) / 2);
                } else {
                    mid1 = (mid1 + 1) + ((hi1 - (mid1 + 1)) / 2);
                }
            } else {
                if (a1[mid1].compareTo(a2[mid2]) < 0) {
                    return a2[mid2];
                } else {
                    return a1[mid1];
                }
            }
        }
    }

    // Question 3 Decimal dominants.
    // here we apply the three way quicksort and get the elemnts

    public static void arrayShuffle(Object[] a) {
        int n = a.length;
        for (int i = 0; i < a.length; i++) {
            Random random = new Random();
            int newIndex = i + random.nextInt(n - i);
            Object tmp = a[i];
            a[i] = a[newIndex];
            a[newIndex] = tmp;
        }
    }

    public static ArrayList<Comparable> threeWay(Comparable[] a) {
        arrayShuffle(a);
        ArrayList<Comparable> result = new ArrayList<Comparable>();
        partition(a, result, 0, a.length - 1);
        return result;
    }

    public static void partition(Comparable[] a, ArrayList<Comparable> result, int lo, int hi) {
        if (hi <= lo)
            return;
        int lt = lo;
        int gt = hi;
        int i = lo + 1;
        while (i <= gt) {

            if (a[i].compareTo(a[lt]) < 0) {
                exch(a, i++, lt++);
            } else if (a[i].compareTo(a[lt]) > 0) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        }
        if (i - lt >= 10) {
            result.add(a[lt]);
        }
        partition(a, result, lo, lt - 1);
        partition(a, result, i + 1, hi);
    }

    public static void exch(Object[] a, int i, int j) {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        // Question 2
        Comparable x1[] = { 2, 3, 6, 7, 9 };
        Comparable x2[] = { 1, 4, 8, 10 };
        System.out.println(selection(x1, x2, 5));

        // Question 3
        Comparable x[] = { 1, 1, 1, 1, 1, 11, 1, 1, 1, 1, 1, 2, 3, 4, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3, 3, 3, 3, 3,
                3, 3, 3, 5, 6 };
        ArrayList<Comparable> result = threeWay(x);
        for (Comparable comparable : x) {
            System.out.println(comparable);
        }
        System.out.println("..............................");
        for (Comparable comparable : result) {
            System.out.println(comparable);
        }
    }
}
