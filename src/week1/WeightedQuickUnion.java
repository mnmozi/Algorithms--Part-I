package week1;

public class WeightedQuickUnion {
    int[] id;
    private int[] sz;
    private int[] largest;

    public WeightedQuickUnion(int n) {
        id = new int[n];
        sz = new int[n];
        largest = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            largest[i] = i;
            sz[i] = 1;

        }
    }

    private int root(int i) {
        if (id[i] == i) {
            return i;
        } else {
            id[i] = id[id[i]]; // path compresion
            return root(id[i]);
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public int find(int i) {
        return largest[root(i)];
    }

    public void remove(int p) {
        union(p, p + 1);

    }

    public int successor(int p) {
        return largest[root(p + 1)];
    }

    public int union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);
        if (rootP == rootQ)
            return sz[rootP];
        largest[rootP] = largest[rootQ] > largest[rootP] ? largest[root(rootQ)] : largest[root(rootP)];

        if (sz[rootP] < sz[rootQ]) {
            id[rootP] = rootQ;
            sz[rootQ] += sz[rootP];

            // set the largest
            // largestSet(rootP, rootQ);
            return sz[rootQ];
        } else {
            id[rootQ] = rootP;
            sz[rootP] += sz[rootQ];
            return sz[rootP];

        }
    }
}
