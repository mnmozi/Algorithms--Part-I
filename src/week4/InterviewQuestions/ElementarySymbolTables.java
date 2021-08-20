package week4.InterviewQuestions;

public class ElementarySymbolTables {

    // Question 1 Java autoboxing and equals()

    // find values such as (a == b) is true and x.equals(y) is false

    // == compares to see if they point to the same memory
    // equals compares the two objects to see if they have the smae value

    // 1- a = 0 b = -0
    // 2- a = b = Double.NaN
    // NaN is by definition not equal to any number including NaN

    // Question 2 Check if a binary tree is a BST.

    // we can make the function recursive and pass the min and max
    class Node {
        Node left;
        Node right;
        Double value;
    }

    class checkBST {
        Node tree;

        public boolean isBST() {
            boolean x1 = isBSTHelper(tree.left, Double.NEGATIVE_INFINITY, tree.value);
            if (x1 == false)
                return false;
            boolean x2 = isBSTHelper(tree.right, tree.value, Double.POSITIVE_INFINITY);
            if (x2 == false)
                return false;

            return true;
        }

        public boolean isBSTHelper(Node currTree, Double min, Double max) {
            if (currTree.left != null) {
                if (currTree.value > currTree.left.value) {
                    return isBSTHelper(currTree.left, min, currTree.left.value);
                } else
                    return false;
            }
            if (currTree.right != null) {
                if (currTree.value < currTree.right.value) {
                    return isBSTHelper(currTree.right, currTree.value, min);
                } else
                    return false;
            }
            return true;
        }

    }

    // Question 3 Inorder traversal with constant extra space
    public static void morrisInorder(Node currNode) {
        while (currNode != null) {
            if (currNode.left == null) {
                System.out.println(currNode.value);
                currNode = currNode.right;
            } else {

                Node predecessor = getPredecessor(currNode.left);
                if (predecessor.right == null) {
                    predecessor.right = currNode;
                    currNode = currNode.left;

                } else {
                    predecessor.right = null;
                    System.out.println(currNode);
                    currNode = currNode.right;
                }

            }
        }

    }

    public static Node getPredecessor(Node currNode) {
        while (currNode.right != null) {
            currNode = currNode.right;
        }
        return currNode;
    }

    // Question 4 Web tracking.
    // keep symbol table of symbol table (IS IT THE BEST WAY TO SOLVE IT??)

}
