public class 之字形打印矩阵 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
        };
        printMatrixZigZag(matrix);
    }

    public static void printMatrixZigZag(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int R1 = 0;
        int C1 = 0;
        int R2 = 0;
        int C2 = 0;
        boolean fromUp = false;
        for (int i = 0; i < rows + cols - 1; i++) {
            if (fromUp) {
                printLevel(matrix, R1, C1, fromUp);
            } else {
                printLevel(matrix, R2, C2, fromUp);
            }
            R1 = C1 == cols - 1 ? R1 + 1 : R1;
            C1 = C1 == cols - 1 ? C1 : C1 + 1;
            //第一次写下面两句弄反了
            C2 = R2 == rows - 1 ? C2 + 1 : C2;
            R2 = R2 == rows - 1 ? R2 : R2 + 1;
            fromUp = !fromUp;
        }
    }

    private static void printLevel(int[][] matrix, int R, int C, boolean fromUp) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        if (fromUp) {
            while (R < rows && C >= 0) {
                System.out.print(matrix[R++][C--] + " ");
            }
        } else {
            while (R >= 0 && C < cols) {
                System.out.print(matrix[R--][C++] + " ");
            }
        }
    }

}
