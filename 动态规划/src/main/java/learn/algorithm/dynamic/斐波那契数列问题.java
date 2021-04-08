package learn.algorithm.dynamic;

public class 斐波那契数列问题 {
    public static void main(String[] args) {
        for(int i = 1; i < 30; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }


    public static int fibonacci(short n) {
        if (n <= 2) return 1;
        else return fibonacci(n - 1) + fibonacci(n - 2);
    }


    public static int fibonacci(int n) {
        if (n <= 2) return 1;
        int next = 0;
        int cur = 1;
        int pre = 1;
        for (int i = 3; i <= n; i++) {
            next = cur + pre;
            pre = cur;
            cur = next;
        }
        return next;
    }


    public static long fibonacci(long n) {
        return 0;
    }

}
