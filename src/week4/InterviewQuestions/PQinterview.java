package week4.InterviewQuestions;

import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.MinPQ;
import week4.MaxoPQ;

public class PQinterview {
    public static void main(String[] args) {

    }
}

// the idea is to have two arrays that will give me the two mid points
// the first array will have the largest value in it (this is the mid point)
// the second array the min one is the mid point
class constMedian {
    MaxPQ<Integer> maxHeap;
    MinPQ<Integer> minHeap;
    int median;

    public constMedian() {
        maxHeap = new MaxPQ<Integer>();
        minHeap = new MinPQ<Integer>();
        median = 0;
    }

    public void insert(int input) {
        // check the median
        if (input > median) {
            minHeap.insert(input);
        } else {
            maxHeap.insert(input);
        }
        int diff = minHeap.size() - maxHeap.size();
        if (diff > 1) {
            int min = minHeap.delMin();
            maxHeap.insert(min);
        } else if (diff < -1) {
            int max = maxHeap.delMax();
            minHeap.insert(max);
        }
        setMedian();
    }

    public void setMedian() {
        if (maxHeap.size() > minHeap.size()) {
            median = maxHeap.max();
        } else if (maxHeap.size() < minHeap.size()) {
            median = minHeap.min();
        } else
            median = (maxHeap.max() + minHeap.min()) / 2;
    }

    public int getMedian() {
        return median;
    }

    public static void main(String[] args) {
        constMedian test = new constMedian();
        test.insert(1);
        test.insert(2);
        test.insert(9);
        System.out.println(test.getMedian());
        test.insert(11);
        System.out.println(test.getMedian());
    }
}

class randomizedPriorityQueue {
    public static void main(String[] args) {
        MaxoPQ<Integer> test = new MaxoPQ<Integer>(5);
        test.insert(1);
        test.insert(2);
        test.insert(2);
        test.insert(4);
        System.out.println(test.sample());
        System.out.println(test.delRandom());

    }
}

// here we changed the sort to print when it finds a match
class taxicabNumbers {
    public static void main(String[] args) {
        int[] inputArray = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
        int n = inputArray.length - 1;

        MaxoPQ<Integer> pQ = new MaxoPQ<Integer>(((n * (n + 1)) / 2));

        for (int i = 0; i < inputArray.length - 1; i++) {
            for (int j = i + 1; j < inputArray.length; j++) {
                int sum = (int) (Math.pow(inputArray[i], 3) + (int) Math.pow(inputArray[j], 3));

                pQ.insert(sum);
            }
        }
        pQ.heapSort();
        System.out.println("hello");
    }

}

// https://algs4.cs.princeton.edu/24pq/Taxicab.java.html
// https://stackoverflow.com/questions/12243571/how-to-find-all-taxicab-numbers-less-than-n
// //Maria Sakharova's answer is perfect
class taxicabNumbers2 implements Comparable<taxicabNumbers2> {
    int i;
    int j;
    long sum;

    taxicabNumbers2(int i, int j) {
        this.i = i;
        this.j = j;
        this.sum = (long) i * i * i + (long) j * j * j;
    }

    public int compareTo(taxicabNumbers2 that) {
        if (this.sum < that.sum)
            return -1;
        else if (this.sum > that.sum)
            return 1;
        else if (this.i < that.i)
            return -1;
        else if (this.i > that.i)
            return 1;
        return 0;
    }

    public static void main(String[] args) {
        int n = 12;
        MinPQ<taxicabNumbers2> pq = new MinPQ<taxicabNumbers2>();
        // here we added tha diagonal
        for (int i = 1; i <= n; i++) {
            pq.insert(new taxicabNumbers2(i, i));
        }
        taxicabNumbers2 prev = new taxicabNumbers2(-1, -1);
        while (!pq.isEmpty()) {
            taxicabNumbers2 curr = pq.delMin();

            if (prev.sum == curr.sum) {
                System.out.println(curr.sum);
            }
            prev = curr;
            if (curr.i < n)
                pq.insert(new taxicabNumbers2(curr.i + 1, curr.j));
        }

    }

}