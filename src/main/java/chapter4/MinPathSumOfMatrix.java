package chapter4;

public class MinPathSumOfMatrix {
    public static void main(String[] args) {

    }

    // space complexity O(m*n)
    public static int getMinPathSum1(int[][] matrix) {
        int[][] help = new int[matrix.length][matrix[0].length];
        help[0][0] = matrix[0][0];
        for (int i = 1; i < matrix.length; i++)
            help[i][0] = help[i - 1][0] + matrix[i][0];
        for (int i = 1; i < matrix[0].length; i++)
            help[0][i] = help[0][i - 1] + matrix[0][i];
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                help[i][j] = Math.min(help[i - 1][j], help[i][j - 1]) + matrix[i][j];
            }
        }
        return help[help.length - 1][help[0].length - 1];
    }


    //space complexity O(m)
    public static int getMinPathSum2(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[] dp = new int[row];
        dp[0] = matrix[0][0];
        for (int i = 1; i < row; i++) {
            dp[i] = dp[i - 1] + matrix[0][i];
        }
        for (int i = 1; i < col; i++) {
            dp[0] = dp[0] + matrix[i][0];
            for (int j = 1; j < row; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + matrix[i][j];
            }
        }
        return dp[row - 1];
    }

    //space complexity  O(min(m,n))
    public static int getMinPathSum3(int[][] matrix) {
        int more = Math.max(matrix.length, matrix[0].length);
        int less = Math.min(matrix.length, matrix[0].length);
        boolean rowMore = more != matrix.length;
        int[] dp = new int[less];
        dp[0] = matrix[0][0];
        for (int i = 1; i < less; i++) {
            dp[i] = dp[i - 1] + (rowMore ? matrix[0][i] : matrix[i][0]);
        }
        for (int i = 1; i < more; i++) {
            dp[0] = dp[0] + (rowMore ? matrix[0][i] : matrix[i][0]);
            for (int j = 1; j < less; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + (rowMore ? matrix[i][j] : matrix[j][i]);
            }
        }
        return dp[less - 1];
    }
}
