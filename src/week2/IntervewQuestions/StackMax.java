package week2.IntervewQuestions;

import edu.princeton.cs.algs4.Stack;

// Question 2
// Stack with max. Create a data structure that efficiently supports the stack operations (push and pop) 
//and also a return-the-maximum operation. Assume the elements are real numbers so that you can compare them.

public class StackMax<Item extends Comparable<Item>> {
    Stack<Item> stack;
    Stack<Item> order;

    public StackMax() {
        stack = new Stack<Item>();
        order = new Stack<Item>();

    }

    public void push(Item item) {
        stack.push(item);
        pushMax(item);
    }

    public void pushMax(Item item) {
        if (order.isEmpty()) {
            order.push(item);
        } else {
            if ((order.peek()).compareTo(item) == 1) {
                Item top = order.pop();
                pushMax(item);
                order.push(top);
            } else
                order.push(item);
        }

    }

    public Item popMax() {
        return order.pop();
    }

    // Question 3
    // Java generics. Explain why Java prohibits generic array creation.
    // https://stackoverflow.com/questions/2927391/whats-the-reason-i-cant-create-generic-array-types-in-java
    // https://github.com/eschwabe/interview-practice/blob/master/coursera/algorithms-part1/stacks-and-queues/Generics.txt

    public static void main(String[] args) {
        StackMax<Integer> test = new StackMax<>();
        test.push(5);
        test.push(1);
        test.push(3);
        test.push(8);
        test.push(7);
        System.out.println(test.popMax());
        System.out.println(test.popMax());
        System.out.println(test.popMax());
    }
}
