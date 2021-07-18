package week1.IntervewQuestions;

import java.util.Date;

import edu.princeton.cs.algs4.In;
import week1.WeightedQuickUnion;

public class Interview {

    // Question 1 Social network connectivity
    public static void questionOne() {
        In in = new In("social.txt");
        int n = in.readInt();
        WeightedQuickUnion test = new WeightedQuickUnion(n);
        while (in.hasNextChar()) {
            long timeStamp = in.readLong();
            Date time = new Date(timeStamp * 1000);

            int firstPerson = in.readInt();
            int secondPerson = in.readInt();

            int size = test.union(firstPerson, secondPerson);
            if (size == n) {
                System.out.println("All connected at: " + time);
            }

        }
    }

    // Question 2 Union-find with specific canonical element
    public static void questionTwo() {
        WeightedQuickUnion test = new WeightedQuickUnion(10);
        test.union(1, 2);
        test.union(3, 9);
        test.union(6, 3);

        System.out.println(test.find(1));
        System.out.println(test.find(3));
        System.out.println(test.find(6));
    }

    // Question 3 Successor with delete.
    public static void questionThree() {
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

    public static void main(String[] args) {
        questionTwo();
    }
}
