package week2.Assignment;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        if (args.length > 0) {

            int k = Integer.parseInt(args[0]);
            RandomizedQueue<String> queue = new RandomizedQueue<String>();
            while (!StdIn.isEmpty()) {
                String input = StdIn.readString();
                queue.enqueue(input);
            }
            while (k > 0) {
                StdOut.println(queue.dequeue());
                k--;
            }
        }
    }
}
