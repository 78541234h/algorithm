package chapter5;

public class BinaryStrStatistics {
    public static void main(String[] args) {

    }

    public static void test() {

    }

    public static int statistics(int n) {
        if (n < 1) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        else return statistics(n - 1) + statistics(n - 2);
    }
}
