package week4;

import edu.princeton.cs.algs4.Queue;

/**
 * BST
 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    public class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int count;

        public Node(Key key, Value val, int count) {
            this.key = key;
            this.val = val;
            this.count = count;
        }
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);

    }

    public Node put(Node x, Key key, Value val) {
        if (x == null)
            return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else
            x.val = val;
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int comp = key.compareTo(x.key);
            if (comp < 0)
                x = x.left;
            if (comp > 0)
                x = x.right;
            else
                return x.val;
        }
        return null;
    }

    public Key floor(Key key) {

        Node x = floor(root, key);
        if (x == null)
            return null;

        return x.key;

    }

    private Node floor(Node x, Key key) {
        if (x == null)
            return null;
        // case 1 key equals the key at root
        if (key.compareTo(x.key) == 0)
            return x;

        // case 2
        else if (key.compareTo(x.key) < 0)
            return floor(x.left, key);

        // case 3
        Node t = floor(x.right, key);
        if (t != null)
            return t;
        else
            return x;
    }

    public void delete(Key key) {

    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        return x.count;
    }

    public int rank(Key key) {
        return rank(key, root);

    }

    private int rank(Key key, Node x) {
        if (x == null)
            return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return rank(key, x.left);
        else if (cmp > 0)
            return 1 + size(x.left) + rank(key, x.right);
        else
            return size(x.left);
    }

    public Iterable<Key> iterator() {
        Queue<Key> q = new Queue<Key>();
        inorder(root, q);
        return q;
    }

    private void inorder(Node x, Queue<Key> q) {
        if (x == null)
            return;
        inorder(x.left, q);
        q.enqueue(x.key);
        inorder(x.right, q);
    }
}