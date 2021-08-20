package week5.interviewQuestions;

public class balancedSearchTree {
    /**
     * Question 1 Red–black BST with no extra memory. Describe how to save the
     * memory for storing the color information when implementing a red–black BST.
     */

    /**
     * Just modify the BST. For black node, do nothing. And for red node, exchange
     * its left child and right child. In this case, a node can be justified red or
     * black according to if its right child is larger than its left child.
     * https://stackoverflow.com/questions/16088364/how-to-save-the-memory-when-storing-color-information-in-red-black-trees
     */

    /**
     * 2. Question 2 Document search. Design an algorithm that takes a sequence of
     * nn document words and a sequence of mm query words and find the shortest
     * interval in which the mm query words appear in the document in the order
     * given. The length of an interval is the number of words in that interval.
     */

    /**
     * Here we can store a LLBST where the key is the word and the value is an array
     * that stores the occurance of that word in the document then for each
     * occarance of the first word we loop for the rest of the words and get the
     * nearest one for it and calculate the distance.
     * 
     * we can search for the nearest occurrence by binary search for the index of
     * the last word and get the first largest occurrence
     */

    /**
     * Question 3 Generalized queue. Design a generalized queue data type that
     * supports all of the following operations in logarithmic time (or better) in
     * the worst case.
     */
    /**
     * create a red–black BST where the keys are integers and the values are the
     * items such that the ith largest integer key in the red–black BST corresponds
     * to the ith item in the queue.
     * 
     * 
     * NOTE: I didn't know how the delete work yet in the LLBST
     */
}
