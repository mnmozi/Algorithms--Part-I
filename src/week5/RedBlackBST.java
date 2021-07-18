package week5;

public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static boolean RED = true;
    private static boolean BLACK = false;

    private class Node {
        Key key;
        Value val;
        Node left, right;
        boolean color;

        private Node(Key key, Value val, boolean color) {
            this.key = key;
            this.val = val;
            this.color = color;
        }
    }

    private boolean isRed(Node x) {
        if (x == null)
            return false;
        return x.color == RED;
    }

    private Node rotateLeft(Node cuurentNode) {
        Node aux = cuurentNode.right; // S
        cuurentNode.right = aux.left;
        aux.left = cuurentNode;
        aux.color = cuurentNode.color;
        cuurentNode.color = RED;
        return aux;

    }

    private Node rotateRight(Node currentNode) {
        Node aux = currentNode.left;
        currentNode.left = aux.right;
        aux.right = currentNode;
        aux.color = currentNode.color;
        aux.color = RED;
        return aux;
    }

    private void flipColor(Node currentNode) {
        currentNode.right.color = BLACK;
        currentNode.left.color = BLACK;
        currentNode.color = RED;
    }

    public void put(Key key, Value val) {
        this.root = put(this.root, key, val);
    }

    private Node put(Node currentNode, Key key, Value val) {

        if (currentNode == null)
            return new Node(key, val, RED);

        int cmp = key.compareTo(currentNode.key);
        if (cmp < 0) {
            currentNode.left = put(currentNode.left, key, val);
        } else if (cmp > 0) {
            currentNode.right = put(currentNode.right, key, val);
        } else {
            currentNode.val = val;
        }
        if (isRed(currentNode.right) && !(isRed(currentNode.left)))
            currentNode = rotateLeft(currentNode);
        if (isRed(currentNode.left) && isRed(currentNode.left.left))
            currentNode = rotateRight(currentNode);

        if (isRed(currentNode.left) && isRed(currentNode.right))
            flipColor(currentNode);

        return currentNode;
    }

    public Value get(Key key) {
        Node currentNode = root;
        while (currentNode != null) {
            int cmp = key.compareTo(currentNode.key);
            if (cmp < 0)
                currentNode = currentNode.left;
            else if (cmp > 0)
                currentNode = currentNode.right;
            else
                return currentNode.val;

        }
        return null;
    }

    Node root;

}
