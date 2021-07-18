package Into;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int i = 1;
        String output = "";
        while (!StdIn.isEmpty()) {
            String input = StdIn.readString();
            double x = 1.0 / i;
            boolean result = StdRandom.bernoulli(x);
            if (result) {
                output = input;
            }
            i++;
        }
        System.out.println(output);

    }
}
