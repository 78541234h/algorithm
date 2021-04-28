package learn.algorithm.dynamic;

public class 打气球最大得分 {
    public static void main(String[] args) {
        int[] arr = {3, 2, 5};
        System.out.print(maxScore(arr));
    }

    public static int maxScoreRecursive(int[] arr) {
        int[] help = new int[arr.length + 2];
        help[0] = 1;
        help[help.length - 1] = 1;
        System.arraycopy(arr, 0, help, 1, arr.length);
        return process(help, 1, arr.length);
    }

    private static int process(int[] arr, int x, int y) {
        int left = arr[x - 1];
        int right = arr[y + 1];
        if (x == y) {
            return left * arr[x] * right;
        }
        int score = Math.max(process(arr, x + 1, y) + left * arr[x] * right,
                process(arr, x, y - 1) + left * arr[y] * right);
        for (int i = x + 1; i <= y - 1; i++) {
            score = Math.max(score,
                    process(arr, x, i - 1) + process(arr, i + 1, y) + left * arr[i] * right);
        }
        return score;
    }


    public static int maxScore(int[] arr) {
        int[] help = new int[arr.length + 2];
        help[0] = help[help.length - 1] = 1;
        System.arraycopy(arr, 0, help, 1, arr.length);

        int[][] dp = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length; i++) {
            dp[i][i] = help[i] * help[i + 1] * help[i + 2];
        }
        for (int i = arr.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < arr.length; j++) {
                int score = Math.max(dp[i][j - 1] + help[i] * help[j + 1] * help[j + 2],
                        dp[i + 1][j] + help[i] * help[i + 1] * help[j + 2]);
                for (int k = i + 1; k <= j - 1; k++) {
                    score = Math.max(score,
                            dp[i][k - 1] + dp[k + 1][j] + help[i] * help[k + 1] * help[j + 2]);
                }
                dp[i][j] = score;
            }
        }
        return dp[0][arr.length - 1];
    }
}
