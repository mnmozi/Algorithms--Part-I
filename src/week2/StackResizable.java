package week2;

public class StackResizable {
    String s[];
    int N;

    public StackResizable() {
        s = new String[1];
        N = 0;
    }

    public void push(String item) {
        if (N == s.length)
            resize(2 * s.length);
        s[N++] = item;
    }

    public void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }

    public String pop() {
        String item = s[--N];
        System.out.println(N);
        s[N] = null;
        if (N > 0 && N == s.length / 4)
            resize(s.length / 2);
        return item;
    }

    public static void main(String[] args) {
        StackResizable x = new StackResizable();
        x.push("hello");
        x.push("world");
        x.pop();
        System.out.println(x.N);
    }

}
