package Into;

public class HelloWorld {
    public static int binarySearch(int[] x, int key) {
        int lo = 0;
        int hi = x.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < x[mid])
                hi = mid - 1;
            else if (key > x[mid])
                lo = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Hello, World");
    }
}