package chapter4;

import utils.PrintUtil;

import java.util.Arrays;

public class ReachLinePostionMethods {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        int res = getNum4(7, 4, 9, 5);
        PrintUtil.print(res);
        PrintUtil.newLine();
        PrintUtil.printSepreateLine();
    }

    /**
     * @param posNums
     * @param start
     * @param steps
     * @param aim
     * @return int
     * @description
     * @date 1/22/20
     */
    public static int getNum1(int posNums, int start, int steps, int aim) {
        if (aim < 1 || aim > posNums || start < 1 || start > posNums || steps < 0) return 0;
        if (steps == 0 && start == aim) return 1;
        int res = 0;
        if (start > 1)
            res += getNum1(posNums, start - 1, steps - 1, aim);
        if (start < posNums)
            res += getNum1(posNums, start + 1, steps - 1, aim);
        return res;
    }

    public static int getNum2(int posNums, int start, int steps, int aim) {
        if (start < 0 || aim < 0 || start > posNums || aim > posNums || steps < 0) return 0;
        int[][] dp = new int[posNums + 1][steps + 1];
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }
        return _getNum2(posNums, start, steps, aim, dp);
    }

    private static int _getNum2(int posNums, int start, int steps, int aim, int[][] dp) {
        if (dp[start][steps] != -1) return dp[start][steps];
        if (steps == 0) {
            if (start == aim) {
                dp[start][0] = 1;
                return 1;
            } else {
                dp[start][0] = 0;
                return 0;
            }
        }
        int res = 0;
        if (start > 1)
            res += _getNum2(posNums, start - 1, steps - 1, aim, dp);
        if (start < posNums)
            res += _getNum2(posNums, start + 1, steps - 1, aim, dp);
        dp[start][steps] = res;
        return res;
    }

    public static int getNum3(int posNums, int start, int steps, int aim) {
        int[][] dp = new int[steps + 1][posNums + 1];
        dp[0][aim] = 1;
        for (int i = 1; i <= steps; i++) {
            dp[i][1] = dp[i - 1][2];
            for (int j = 2; j < posNums; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
            }
            dp[i][posNums] = dp[i - 1][posNums - 1];
        }
        return dp[steps][start];
    }

    public static int getNum4(int posNums, int start, int steps, int aim) {
        int[] dp = new int[posNums + 1];
        dp[aim] = 1;
        for (int i = 1; i <= steps; i++) {
            int tmp1 = dp[1];
            dp[1] = dp[2];
            for (int j = 2; j < posNums; j++) {
                int tmp2 = dp[j];
                dp[j] = tmp1 + dp[j + 1];
                tmp1 = tmp2;
            }
            dp[posNums] = tmp1;
        }
        return dp[start];
    }
}