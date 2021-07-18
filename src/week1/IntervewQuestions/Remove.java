package week1.IntervewQuestions;

import edu.princeton.cs.algs4.In;
import week1.WeightedQuickUnion;

// Question 2 and 3 methods added to the WeightedQuickUnion.java
// if works for every remove but when we remove 9 it craches we we remove it we may union it with the prev node and make the largest the large of the prev one
public class Remove {
    public static void main(String[] args) {
        In in = new In("remove.txt");
        int n = in.readInt();
        WeightedQuickUnion test = new WeightedQuickUnion(n);
        System.out.println(n);
        while (in.hasNextChar()) {
            String action = in.readString();
            System.out.println(action);
            int num = in.readInt();
            if (action.equals("r")) {
                test.remove(num);
            } else if (action.equals("s")) {
                int successor = test.successor(num);
                System.out.println("successor is: " + successor);
            }

        }
    }
}
