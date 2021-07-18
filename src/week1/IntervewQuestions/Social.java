package week1.IntervewQuestions;

import java.util.Date;

import edu.princeton.cs.algs4.In;
import week1.WeightedQuickUnion;

public class Social {
    int n; // number of people
    WeightedQuickUnion connections;

    public Social(int n) {
        this.n = n;
        connections = new WeightedQuickUnion(n);
    }

    public static void main(String[] args) {
        // here we read the timestamps file
        In in = new In("social.txt");
        int n = in.readInt();
        Social test = new Social(n);
        System.out.println(n);
        while (in.hasNextChar()) {
            long timeStamp = in.readLong();
            System.out.println(timeStamp);
            Date time = new Date(timeStamp * 1000);

            int firstPerson = in.readInt();
            int secondPerson = in.readInt();

            int size = test.connections.union(firstPerson, secondPerson);
            if (size == n) {
                System.out.println("All connected at: " + time);
            }

        }
        System.out.println(test.connections.find(1));
        System.out.println(test.connections.find(3));
        System.out.println(test.connections.find(6));
    }

}
