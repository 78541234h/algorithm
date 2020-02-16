package chapter4;

import utils.PrintUtil;

import java.util.Arrays;

public class WaysNumOfChangeMoney {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        int[] arr = {5, 10, 25, 1};
        int res = getNum2(arr, 15);
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

    public static int getNum3(int[] arr, int aim) {
        int[][] dp = new int[aim + 1][arr.length];
        for (int i = 1; i < aim + 1; i++) {

        }
        return 0;
    }
}