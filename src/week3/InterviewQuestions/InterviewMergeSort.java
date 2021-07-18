package week3.InterviewQuestions;

import java.util.Random;

public class InterviewMergeSort {
    // Question 1 Merging with smaller auxiliary array

    public static void mergeWithSmallerArray(Comparable[] a) {
        int n = a.length;
        int auxSize = n / 2;
        Comparable[] aux = new Comparable[auxSize];
        sort(a, aux, 0, n - 1);
    }

    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (lo >= hi)
            return;
        int mid = lo + ((hi - lo) / 2);
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // copy the first halp to the aux
        int i = 0;
        for (int k = lo; k <= mid; k++) {
            aux[i++] = a[k];
        }

        int currentAuxSize = mid - lo + 1;
        i = 0;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i >= currentAuxSize) {
                a[k] = a[j++];
            } else if (j > hi)
                a[k] = aux[i++];
            else if (aux[i].compareTo(a[j]) < 0) {
                a[k] = aux[i++];
            } else {
                a[k] = a[j++];
            }
        }
    }

    // ----------
    // Question 2 Counting inversions.

    // we just add a counter and increment it when we find that the element in the
    // right subarray is bigger than the left one

    // Question 3 Shuffling a linked list.
    public class Node {
        Comparable item;
        Node next;

        Node(Comparable item) {
            this.item = item;
            next = null;
        }

        public void add(Comparable item) {
            Node x = this;
            while (x.next != null)
                x = x.next;
            x.next = new Node(item);
        }
    }

    public static Node shuffle(Node list) {
        int n = 0;
        Node counter = list;
        while (counter != null) {
            counter = counter.next;
            n++;
        }
        return shuffle(list, n);
    }

    public static Node shuffle(Node list, int n) {
        if (n == 1) {
            return list;
        }
        Node mid = list;
        int midIndex = n / 2;
        if (n % 2 != 0)
            midIndex += 1;

        for (int i = 1; i < midIndex; i++) {
            mid = mid.next;
        }

        Node rL = mid.next;
        mid.next = null;
        Node lShuffle = shuffle(list, midIndex);
        Node rShuffle = shuffle(rL, n - midIndex);
        Node result = shuffleMerge(lShuffle, rShuffle);
        return result;
    }

    public static Node shuffleMerge(Node lL, Node rL) {
        Node right = rL;
        Node left = lL;

        Random random = new Random();
        Node result;
        if (random.nextInt(2) > 0) {
            lL = right;
            right = right.next;
        } else {
            left = left.next;
        }
        result = lL;
        while (right != null || left != null) {
            if (right == null) {
                result.next = left;
                left = left.next;
            } else if (left == null) {
                result.next = right;
                right = right.next;
            } else if (random.nextInt(2) > 0) {
                result.next = right;
                right = right.next;
            } else {
                result.next = left;
                left = left.next;
            }
            result = result.next;
        }
        return lL;
    }

    public static void main(String[] args) {
        // ----- Question 1 -----
        // Integer[] a = { 82, 54, 14, 23, 90, 9, 7, 5, 2, 1 };
        // mergeWithSmallerArray(a);
        // for (int i = 0; i < a.length; i++) {
        // System.out.print(a[i] + " ");
        // }
        // Random random = new Random();
        // for (int i = 0; i < 10; i++) {
        // System.out.println(random.nextInt(2));
        // }

        // ----- Question 3 -----
        // Integer x = 1;
        // InterviewMergeSort testClass = new InterviewMergeSort();
        // InterviewMergeSort.Node test = testClass.new Node(1);
        // test.add(2);
        // test.add(3);
        // test.add(4);
        // test.add(5);
        // test.add(6);
        // test.add(7);
        // test = shuffle(test);
        // while (test != null) {
        // System.out.println(test.item);
        // test = test.next;
        // }
    }

}
