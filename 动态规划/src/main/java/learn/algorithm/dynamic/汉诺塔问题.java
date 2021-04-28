package learn.algorithm.dynamic;

public class 汉诺塔问题 {
    public static void main(String[] args) {
        hanoi(3);
    }

    public static void hanoi(int n) {
        process(n, "left", "right", "mid");
    }

    private static void process(int n, String from, String to, String by) {
        if (n == 0)
            return;
        process(n - 1, from, by, to);
        System.out.println("move: " + n + " from: " + from + "\tto:\t" + to);
        process(n - 1, by, to, from);
    }
}
