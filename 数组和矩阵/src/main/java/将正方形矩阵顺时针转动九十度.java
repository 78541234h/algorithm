public class 将正方形矩阵顺时针转动九十度 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        rotateMatrix(matrix);
        for (int[] arr : matrix) {
            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }


    public static void rotateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length < 2) return;
        int N = matrix.length;
        for (int i = 0; i < N / 2; i++) {
            rotateEdge(matrix, i);
        }
    }

    private static void rotateEdge(int[][] matrix, int nested) {
        int n = matrix.length - nested * 2;
        for (int i = 0; i < n - 1; i++) {
            int tmp = matrix[nested][nested + i];
            matrix[nested][nested + i] = matrix[nested + n - 1 - i][nested];
            matrix[nested + n - 1 - i][nested] = matrix[nested + n - 1][nested + n - 1 - i];
            matrix[nested + n - 1][nested + n - 1 - i] = matrix[nested + i][nested + n - 1];
            matrix[nested + i][nested + n - 1] = tmp;
        }
    }
}
