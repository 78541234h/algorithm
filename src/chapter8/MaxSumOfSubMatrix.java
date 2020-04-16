package chapter8;

public class MaxSumOfSubMatrix {
    public static void main(String[] args) {

    }

    public int maxSumOfSubMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < row; i++) {
            int[] compact = new int[col];
            for (int j = i; j < row; j++) {
                int sum = 0;
                for (int k = 0; k < col; k++) {
                    compact[k] += matrix[j][k];
                    sum += compact[k];
                    max = Math.max(max, sum);
                    if (sum < 0) sum = 0;
                }
            }
        }
        return max;
    }
}
