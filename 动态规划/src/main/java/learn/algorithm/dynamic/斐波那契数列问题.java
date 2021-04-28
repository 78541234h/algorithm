package learn.algorithm.dynamic;

public class 斐波那契数列问题 {
    public static void main(String[] args) {
        for(long i = 1; i < 30; i++) {
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
        if(n == 1 || n == 2)
            return 1;
        long[][] m = {{1, 1}, {1,0}};
        long[][] res = powerMatrix(m, (int)n - 2);
        return res[0][0] + res[1][0];
    }
    //矩阵的n次幂
    //这个method我第一次写出错了，multiplier和res搞反了。
    public static long[][] powerMatrix(long[][] m, int power) {
        int filter = 1;
        long[][] multiplier = m;
        long[][] res = new long[m.length][m[0].length];
        for(int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        //直接移位判0，从而不用知道二进制位数
        for(; power != 0 ; power >>= 1) {
            if((power & 1) != 0)
                res = multiMatrix(multiplier, res);
            multiplier = multiMatrix(multiplier, multiplier);
        }
        return res;
    }
    //矩阵乘法
    public static long[][] multiMatrix(long[][] m1, long[][] m2) {
        if(m1 == null || m2 == null || m1.length < 1 || m2.length < 1 || m1[0].length != m2.length)
            throw new RuntimeException("param error");
        int rows = m1.length;
        int cols = m2[0].length;
        long[][] res = new long[rows][cols];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                long sum = 0;
                for(int k = 0; k < m2.length; k++) {
                    sum += m1[i][k] * m2[k][j];
                }
                res[i][j] = sum;
            }
        }
        return res;
    }
}
