package week3;

public class ThreeWaySort {

    public static void sort(Comparable[] a) {
        int lo = 0;
        int hi = a.length - 1;

        sort(a, lo, hi);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi)
            return;
        partition(a, lo, hi);

    }

    public static void partition(Comparable[] a, int lo, int hi) {
        int lt = lo;
        int gt = hi;
        int i = lt + 1;
        while (i <= gt) {
            if (a[i].compareTo(a[lt]) < 0) {
                swap(a, i++, lt++);
            }

            else if (a[i].compareTo(a[lt]) > 0) {
                swap(a, i, gt--);
            } else if (a[i].compareTo(a[lt]) == 0) {
                i++;
            }

        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    public static void swap(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        Comparable x[] = { 'p', 'a', 'b', 'x', 'w', 'p', 'p', 'v', 'p', 'd', 'p', 'c', 'y', 'z' };
        sort(x);
        for (Comparable comparable : x) {
            System.out.println(comparable);
        }
    }
}
