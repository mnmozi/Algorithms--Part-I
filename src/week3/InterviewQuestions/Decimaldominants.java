package week3.InterviewQuestions;

import java.util.ArrayList;
import java.util.Random;

public class Decimaldominants {
    // Question 3
    // given an array with n keys, design an algorithm to find all values that
    // occur more than n/10 times. The expected running time of your algorithm
    // should be linear.
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
