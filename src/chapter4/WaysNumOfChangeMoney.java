package chapter4;

import utils.PrintUtil;

import java.util.Arrays;


//给定几种货币和要找钱的金额，问有多少种换钱的方法
//递归 &　动态规划
public class WaysNumOfChangeMoney {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        int[] arr = {5, 10, 25, 1};
        int res = getNum4(arr, 15);
        PrintUtil.print(res);
        PrintUtil.newLine();
    }

    public static int getNum(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 0) return 0;
        return _getNum(arr, arr.length - 1, aim);
    }

    private static int _getNum(int[] arr, int kinds, int aim) {
        if (aim == 0) return 1;
        if (kinds == -1) return 0;
        int res = 0;
        for (int i = 0; arr[kinds] * i <= aim; i++) {
            res += _getNum(arr, kinds - 1, aim - arr[kinds] * i);
        }
        return res;
    }

    public static int getNum2(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 0) return 0;
        int[][] cache = new int[arr.length][aim + 1];
        for (int[] ints : cache)
            Arrays.fill(ints, -1);
        return _getNum2(arr, arr.length - 1, aim, cache);
    }

    private static int _getNum2(int[] arr, int kinds, int aim, int[][] cache) {
        if (aim == 0) return 1;
        if (kinds == -1) return 0;
        if (cache[kinds][aim] != -1) return cache[kinds][aim];
        int res = 0;
        for (int i = 0; i * arr[kinds] <= aim; i++)
            res += _getNum2(arr, kinds - 1, aim - i * arr[kinds], cache);
        cache[kinds][aim] = res;
        return res;
    }

    //自己写的动态规划，有枚举的过程，其实可以把枚举省略
    public static int getNum3(int[] arr, int aim) { //动态规划的方法
        if (aim == 0) return 1;
        if (arr == null || arr.length < 1 || aim < 0) return 0;
        int[][] dp = new int[arr.length][aim + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= aim; i++) {
            if (i % arr[0] == 0)
                dp[0][i] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            dp[i][0] = 1;
            for (int j = 1; j < aim + 1; j++) {
                for (int k = 0; k * arr[i] <= j; k++) {
                    dp[i][j] += dp[i - 1][j - k * arr[i]];
                }
            }
        }
        return dp[arr.length - 1][aim];
    }


    //动态规划　＋　状态压缩
    public static int getNum4(int[] arr, int aim) {
        int[] dp = new int[aim + 1];
        for (int i = 0; i * arr[0] <= aim; i++) {
            dp[i * arr[0]] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j <= aim; j++) {
                dp[j] += j - arr[i] >= 0 ? dp[j - arr[i]] : 0;
            }
        }
        return dp[aim];
    }
}