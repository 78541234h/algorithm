package learn.algorithm.dynamic;

public class 龙与地下城游戏 {
    public static void main(String[] args) {
    }

    public static int minBlood(int[][] map) {
        int rows = map.length;
        int cols = map[0].length;
        int[][] dp = new int[rows][cols];
        dp[rows - 1][cols - 1] = Math.max(1, 1 - map[rows - 1][cols - 1]);
        for (int j = cols - 2; j >= 0; j--) {
            dp[rows - 1][j] = Math.max(dp[rows - 1][j + 1] - map[rows - 1][j], 1);
        }
        for (int i = rows - 2; i >= 0; i--) {
            dp[i][cols - 1] = Math.max(dp[i + 1][cols - 1] - map[i][cols - 1], 1);
            for (int j = cols - 2; j >= 0; j--) {
                int right = Math.max(dp[i][j + 1] - map[i][j], 1);
                int below = Math.max(dp[i + 1][j] - map[i][j], 1);
                dp[i][j] = Math.min(below, right);
            }
        }
        return dp[0][0];
    }
}
