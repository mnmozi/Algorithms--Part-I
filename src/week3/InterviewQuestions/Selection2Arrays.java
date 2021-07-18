package week3.InterviewQuestions;

public class Selection2Arrays {

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

    public static void main(String[] args) {
        Comparable x1[] = { 2, 3, 6, 7, 9 };
        Comparable x2[] = { 1, 4, 8, 10 };
        System.out.println(selection(x1, x2, 5));
    }
}
