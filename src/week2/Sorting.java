package week2;

import java.util.Random;

public class Sorting {

    public static void selectionSort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i; j < a.length; j++) {
                if (a[j].compareTo(a[min]) == -1)
                    min = j;
            }
            exch(a, i, min);
        }
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable x = a[i];
        a[i] = a[j];
        a[j] = x;
    }

    public static void insertSort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[j].compareTo(a[i]) == -1)
                    exch(a, i, j);
            }
        }
    }

    public static void shellSort(Comparable[] a) {
        int n = a.length;

        int h = 1;
        // 3x+1 increment sequence: 1, 4, 13, 40, 121, 364, 1093, ...
        while (h < n / 3)
            h = h * 3 + 1;

        while (h >= 1) {

            for (int i = 0; i < n; i++) {
                for (int j = i + h; j < n; j += h) {
                    if (a[j].compareTo(a[i]) == -1)
                        exch(a, i, j);
                }
            }
            h = h / 3;
        }
    }

    // ----- Merge Sort -----
    public static void sort(Comparable[] list) {
        Comparable[] aux = list.clone();
        sort(aux, list, 0, list.length - 1);

    }

    public static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
        if (hi <= lo)
            return;

        int mid = lo + (hi - lo) / 2;

        sort(dst, src, lo, mid);
        sort(dst, src, mid + 1, hi);
        // if (src[mid].compareTo(src[mid + 1]) < 0) {
        // System.arraycopy(src, lo, dst, lo, hi - lo + 1);
        // return;
        // }
        merge(src, dst, lo, mid, hi);

    }

    public static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        // for (int i = lo; i <= hi; i++) {
        // aux[i] = list[i];
        // }
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                dst[k] = src[j++];
            else if (j > hi)
                dst[k] = src[i++];
            else if (src[i].compareTo(src[j]) > 0)
                dst[k] = src[j++];
            else
                dst[k] = src[i++];
        }

    }

    // ----------
    // -----Bottom up merge-----
    public static void bUMergeSort(Comparable[] array) {
        Comparable[] aux = new Comparable[array.length];
        for (int i = 1; i < array.length; i *= 2) {
            for (int j = 0; j < array.length - i; j += i + i) {
                int mid = j + i - 1;
                int hi = Math.min(j + i + i - 1, array.length - 1);
                mergeBU(array, aux, j, mid, hi);

            }
        }
    }

    public static void mergeBU(Comparable[] array, Comparable[] aux, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            aux[i] = array[i];
        }
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                array[k] = aux[j++];
            else if (j > hi)
                array[k] = aux[i++];
            else if (aux[i].compareTo(aux[j]) > 0)
                array[k] = aux[j++];
            else
                array[k] = aux[i++];
        }

    }

    // -----QuickSort-----
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

    public static void sortQ(Comparable[] array) {
        shuffle(array);
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(Comparable[] array, int lo, int hi) {
        if (lo >= hi)
            return;
        int v = partition(array, lo, hi);
        quickSort(array, lo, v - 1);
        quickSort(array, v + 1, hi);

    }

    public static int partition(Comparable[] array, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (array[lo].compareTo(array[++i]) == 1) {
                if (i == hi)
                    break;
            }
            while (array[lo].compareTo(array[--j]) == -1) {
                if (j == lo)
                    break;
            }
            if (i >= j) {
                break;
            }
            exch(array, i, j);
        }
        exch(array, lo, j);
        return j;
    }

    public static void main(String[] args) {
        Integer[] a = { 11, 10, 5, 1, 4, 2, 9 };
        sortQ(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
