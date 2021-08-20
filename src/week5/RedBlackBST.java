package week5;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        private Key key; // key
        private Value val; // associated data
        Node left, right; // links to the left and right subtree
        private boolean color; // color of parent link
        private int size; // subtree count

        public Node(Key key, Value val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }

    }

    /**
     * Initialize an empty symbol table.
     */
    public RedBlackBST() {

    }

    /*****************************************************************
     * Node helper methods.
     ******************************************************************/

    // is node x red; false if x is null ?
    private boolean isRed(Node x) {
        if (x == null)
            return false;
        return x.color;
    }

    // number of nodes in subtree rooted at x ; - if x is null
    private int size(Node x) {
        if (x == null)
            return 0;
        return x.size;
    }

    /**
     * Return the number of key-value pairs in this symbol table.
     * 
     * @return the number of Key-value pairs in this symbol table
     */
    public int size() {
        return size(root);
    }

    /**
     * Is this symbol table empty?
     * 
     * @return {@code true} if this symbol table is empty and {@code false}
     *         otherwise
     */
    public boolean isEmpty() {
        return this.root == null;
    }

    /***************************************************************************
     * Standard BST search.
     ***************************************************************************/
    /**
     * Returns the value associated with the given key.
     * 
     * @param key the key
     * @return the value associated with the given key and {@code null} if the key
     *         is not in the symbol table
     * @throws IllegalArgumentException if {@code Key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to get is null");
        return get(this.root, key);
    }

    // value associated with the given key in subtree rooted at x; ; null if no such
    // key
    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp > 0)
                x = x.right;
            else if (cmp < 0)
                x = x.left;
            else
                return x.val;

        }
        return null;
    }

    /**
     * 
     * @param key
     * @return {@code true} is this symbol table contains {@code key} and
     *         {@code false} otherwise
     * 
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /*****************************************************************
     * Red-Black tree insertino.
     ******************************************************************/
    /**
     * Insert the specified key-value pair into the symbol table, overwriting the
     * old value with the new value if the symbol table already contains the key.
     * Deletes the specified key (and it's associated value) from the symbol table
     * if the specified value is {@code null}.
     */
    public void put(Key key, Value val) {
        if (key == null)
            throw new IllegalArgumentException("Key value (the first argument) to put() is null");
        // if (val == null){
        // delete (key);
        // return;
        // }
        root = put(root, key, val);
        root.color = BLACK;
    }

    // insert the key-value pair in the subtree rooted at h.
    private Node put(Node h, Key key, Value val) {
        if (h == null)
            return new Node(key, val, RED, 1);

        int cmp = key.compareTo(h.key);
        if (cmp > 0)
            h.right = put(h.right, key, val);
        else if (cmp < 0)
            h.left = put(h.left, key, val);
        else
            h.val = val;

        // fix-up any right-leaning links
        if (isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))
            flipColors(h);
        h.size = size(h.left) + size(h.right) + 1;

        return h;
    }

    /*****************************************************************
     * Red-Black tree helper functions.
     ******************************************************************/
    // make a left-leaning link lean to the right
    private Node rotateLeft(Node h) {

        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        assert (h != null) && isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h) {

        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    /***************************************************************************
     * Utility functions.
     ***************************************************************************/
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null)
            return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    /***************************************************************************
     * Ordered symbol table methods.
     ***************************************************************************/

    /**
     * Returns the smallest key in the symbol table.
     * 
     * @return the smallest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key min() {
        if (isEmpty())
            throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    // the smallest key in subtree rooted at x
    private Node min(Node x) {
        if (x.left == null)
            return x;
        else
            return min(x.left);
    }

    /**
     * Returns the largest key in the symbol table
     * 
     * @return the largest key in the symbol table
     * @exception NoSuchElementException if the symbol table is empty
     */
    public Key max() {
        if (isEmpty())
            throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }

    // the largest key in the subtree rooted at x
    private Node max(Node x) {
        if (x.right == null)
            return x;
        else
            return max(x.right);
    }

    /**
     * Returns the largest key in the symbol table less than or equal to
     * {@code key}.
     * 
     * @param key the key
     * @return the largest key in the symbol table less than or equal to {@code key}
     * @throws NoSuchElementException   if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key floor(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty())
            throw new NoSuchElementException("calls floor() with empty symbol table");
        Node x = floor(root, key);
        if (x == null)
            throw new NoSuchElementException("argument to floor() is too small");
        else
            return x.key;
    }

    // the largest key in the subtree rooted at x less that or equal to the given
    // key
    private Node floor(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            return x;
        if (cmp < 0)
            return floor(x.left, key);

        Node t = floor(x.right, key);
        if (t != null)
            return t;
        else
            return x;
    }

    /**
     * Return the smallest key in the symbol table greater than or equal to
     * {@code key}
     * 
     * @param key the key
     * @return the smallest key in the symbol table greater than or equal to
     *         {@code key}
     * @throws NoSuchElementException   if there is no such key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key ceilling(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to ceilling() is null");
        if (isEmpty())
            throw new NoSuchElementException("calls ceilling() to empty argument");
        Node x = ceilling(root, key);
        if (x == null)
            throw new NoSuchElementException("");
        else
            return x.key;
    }

    // the smallest key in the subtree rooted at x greater than or equal to the
    // given key
    private Node ceilling(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            return x;
        if (cmp > 0)
            return ceilling(x.right, key);
        Node t = ceilling(x.left, key);
        if (t != null)
            return t;
        else
            return x;
    }

    public int rank(Key key) {
        if (key == null)
            throw new IllegalArgumentException("argument to rank() is null");
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null)
            return 0;
        int cmp = key.compareTo(x.key);
        if (cmp > 0)
            return size(x.left) + 1 + rank(x.right, key);
        else if (cmp < 0)
            return rank(x.left, key);
        else
            return size(x.left);
    }

    /**
     * Return the key in the symbol table of a given {@code rank}. This key has the
     * property that there are {@code rank} keys in the symbol table that are
     * smaller. In other words, this key is the ({@code rank}+1)st smallest key in
     * the symbol table.
     * 
     * @param rank the order statistic
     * @return the key in the symbol table of given {@code rank}
     * @throws IllegalArgumentException unless {@code rank} is between 0 and n-1
     */
    public Key select(int rank) {
        if (rank < 0 || rank > size()) {
            throw new IllegalArgumentException("argument to select is invalid: ");
        }
        return select(root, rank);
    }

    // Return key in BST rooted at x of given rank.
    private Key select(Node x, int rank) {
        if (x == null)
            return null;
        int leftSize = size(x.left);
        if (leftSize > rank)
            return select(x.left, rank);
        else if (leftSize < rank)
            return select(x.right, rank - leftSize - 1);
        else
            return x.key;
    }

    /***************************************************************************
     * Range count and range search.
     ***************************************************************************/
    public Iterable<Key> Keys() {
        if (isEmpty())
            return new ArrayList<Key>();
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null)
            throw new IllegalArgumentException("fist argument to keys() is null");
        if (hi == null)
            throw new IllegalArgumentException("second argument to keys() is null");
        ArrayList<Key> arrayList = new ArrayList<Key>();
        keys(root, arrayList, lo, hi);
        return arrayList;
    }

    public void keys(Node x, ArrayList<Key> arrayList, Key lo, Key hi) {
        if (x == null)
            return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0)
            keys(x.left, arrayList, lo, hi);
        if (cmplo <= 0 && cmphi >= 0)
            arrayList.add(x.key);
        if (cmphi > 0)
            keys(x.right, arrayList, lo, hi);
    }

    public int size(Key lo, Key hi) {
        if (lo == null)
            throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null)
            throw new IllegalArgumentException("second argument to size() is null");

        if (lo.compareTo(hi) > 0)
            return 0;
        if (contains(hi))
            return rank(hi) - rank(lo) + 1;
        else
            return rank(hi) - rank(lo);
    }

}
