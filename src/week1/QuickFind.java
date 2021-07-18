package week1;

import edu.princeton.cs.algs4.In;

public class QuickFind {
    int[] id;

    public QuickFind(int n) {
        System.out.println("herer" + n);
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        errorCheck(p, q);
        return id[p] == id[q];
    }

    // change all entries with id[p] to id[q]
    // at most 2N+2 array axxesses
    public void union(int p, int q) {

        errorCheck(p, q);

        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid)
                id[i] = qid;
        }
    }

    private void errorCheck(int p, int q) {
        int n = id.length;
        if (p > n - 1 || q > n - 1 || p < 0 || q < 0) {
            throw new IllegalArgumentException("Can't make a grid with 0 or less size");
        }
    }

    public static void main(String[] args) {
        In in = new In();
        String n = in.readLine();
        QuickFind testArray = new QuickFind(Integer.parseInt(n));
        while (in.hasNextLine()) {
            String input = in.readLine();
            String[] inputsDivided = input.split(" ");
            int first = Integer.parseInt(inputsDivided[1]);
            int second = Integer.parseInt(inputsDivided[2]);
            System.out.println(inputsDivided[0]);
            if (inputsDivided[0].equals("u"))
                testArray.union(first, second);
            else if (inputsDivided[0].equals("c"))
                System.out.println(testArray.connected(first, second));
            else
                System.out.println(
                        "You need to enter either < c # # > to check if the two number are connected OR < u # # > to connect the two numbers )");
        }

    }
}
