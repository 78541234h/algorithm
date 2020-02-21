package chapter4;

import com.sun.xml.internal.org.jvnet.mimepull.CleanUpExecutorFactory;
import utils.PrintUtil;

public class MaxScoreOfShootingBalloons {
    public static void main(String[] args) {
        test();
    }

    public static int maxScoreOfShootingBallons(int[] arr) {
        return 0;
    }

    public static void test() {
        int[] arr = {3, 2, 5};
        //int maxScore = maxScoreRecur2(arr);
        int maxScore = maxScoreDynamicProcess(arr);
        PrintUtil.print(maxScore);
    }

    public static int maxScoreRecur(int[] arr) {
        int[] tmp = new int[arr.length];
        System.arraycopy(arr, 0, tmp, 0, arr.length);
        return _maxScoreRecur(tmp);
    }

    private static int _maxScoreRecur(int[] arr) {
        int maxScore = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != -1) {
                int score = 0;
                int balloon = arr[i];
                arr[i] = -1;
                int left = i, right = i;
                while (--left >= 0 && arr[left] == -1) ;
                while (++right < arr.length && arr[right] == -1) ;
                if (left >= 0 && right < arr.length)
                    score += balloon * arr[left] * arr[right];
                else if (left >= 0)
                    score += balloon * arr[left];
                else if (right < arr.length)
                    score += balloon * arr[right];
                else
                    score += balloon;
                score += _maxScoreRecur(arr);
                maxScore = Math.max(maxScore, score);
                arr[i] = balloon;
            }
        }
        return maxScore;
    }

    public static int maxScoreRecur2(int[] arr) {
        if (arr == null || arr.length < 1) return 0;
        if (arr.length == 1) return arr[0];
        int[] help = new int[arr.length + 2];
        System.arraycopy(arr, 0, help, 1, arr.length);
        help[0] = help[arr.length + 1] = 1;
        return process(help, 1, arr.length);
    }

    private static int process(int[] arr, int left, int right) {
        if (left == right) return arr[left - 1] * arr[left] * arr[right + 1];
        int max = Math.max(process(arr, left + 1, right) + arr[left - 1] * arr[left] * arr[right + 1],
                process(arr, left, right - 1) + arr[left - 1] * arr[right] * arr[right + 1]);
        for (int i = left + 1; i < right; i++) {
            max = Math.max(process(arr, left, i - 1) +
                            process(arr, i + 1, right) +
                            arr[left - 1] * arr[i] * arr[right + 1],
                    max);
        }
        return max;
    }

    public static int maxScoreDynamicProcess(int[] arr) {
        int[][] dp = new int[arr.length + 2][arr.length + 2];
        int[] help = new int[arr.length + 2];
        help[0] = help[arr.length + 1] = 1;
        System.arraycopy(arr, 0, help, 1, arr.length);
        for (int i = arr.length; i > 0; i--) {
            dp[i][i] = help[i - 1] * help[i] * help[i + 1];
            for (int j = i + 1; j < arr.length + 1; j++) {
                for (int k = i; k <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], help[i - 1] * help[k] * help[j + 1] + dp[i][k - 1] + dp[k + 1][j]);
                }
            }
        }
        return dp[1][arr.length];
    }
}
