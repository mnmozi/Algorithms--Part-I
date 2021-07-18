package week2;

public class StackFixedArray<Item> {
    private Item[] s;
    private int N = 0;

    public StackFixedArray(int capacity) {
        s = (Item[]) new Object[capacity]; // ugly cast
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(Item item) {
        s[N++] = item;
    }

    public Item pop() {
        return s[--N];
    }
}
