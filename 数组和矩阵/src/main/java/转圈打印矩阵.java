import java.util.Vector;

public class 转圈打印矩阵 {

    public static void main(String[] args) {
        int[][] matrix = {
//                {7},{9},{6}
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        int[] res = printByCircle(matrix);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }


    public static int[] printByCircle(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return new int[0];

        int rows = matrix.length;
        int cols = matrix[0].length;
        int nested = (Math.min(rows, cols) + 1) / 2;
        int[] res = new int[rows * cols];
        int cur = 0;
        for (int i = 0; i < nested; i++) {
            cur = collectEdge(matrix, i, i, rows - i * 2, cols - i * 2, res, cur);
        }
        return res;
    }

    private static int collectEdge(int[][] matrix, int y, int x, int rows, int cols, int[] res, int cur) {
        for (int i = x; i < x + cols; i++) {
            res[cur++] = matrix[y][i];
        }
        if (rows > 1) {
            for (int j = y + 1; j < y + rows; j++) {
                res[cur++] = matrix[j][x + cols - 1];
            }

            for (int i = x + cols - 2; i >= x; i--) {
                res[cur++] = matrix[y + rows - 1][i];
            }
            //第一次写这里弄错了，弄成了row > 2
            if (cols > 1) {
                for (int j = y + rows - 2; j > y; j--) {
                    res[cur++] = matrix[j][x];
                }
            }
        }
        return cur;
    }
}
