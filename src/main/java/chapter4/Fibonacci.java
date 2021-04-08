package chapter4;

public class Fibonacci {
    public static int[][] matrixPower(int[][] matrix, int power) {
        int[][] res = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            res[i][i] = 1;
        }
        int[][] tmp = matrix;
        for (; power != 0; power >>= 1) {
            if ((power & 1) != 0) {
                res = matrixMulti(tmp, res);
            }
            tmp = matrixMulti(tmp, tmp);
        }
        return res;
    }

    public static int[][] matrixMulti(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    int fibonacci(int n) {
        if (n < 1) return 0;
        if (n == 1 || n == 2)
            return 1;
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[1][0];
    }
}
