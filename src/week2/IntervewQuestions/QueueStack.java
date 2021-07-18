package week2.IntervewQuestions;

import edu.princeton.cs.algs4.Stack;

// Question 1
// Queue with two stacks. Implement a queue with two stacks so that each queue operations takes a constant amortized number of stack operations.
public class QueueStack<Item> {
    Stack<Item> inbox;
    Stack<Item> outbox;

    public QueueStack() {
        inbox = new Stack<Item>();
        outbox = new Stack<Item>();
    }

    public void queue(Item item) {
        inbox.push(item);
    }

    public Item dequeue() {
        if (outbox.isEmpty()) {
            while (inbox.isEmpty()) {
                outbox.push(inbox.pop());
            }

        }
        return outbox.pop();
    }
}
