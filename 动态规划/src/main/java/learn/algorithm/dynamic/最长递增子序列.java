package learn.algorithm.dynamic;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class 最长递增子序列 {
    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 3, 6, 4, 8, 9, 7};
        int[] res = longestIncreasingSubsequenceBetter(arr);
        for (int i : res) {
            System.out.print(i + " ");
        }
//        Stream.<int[]>of(longestIncreasingSubsequence(arr)).forEach(System.out::println);
    }


    public static int[] longestIncreasingSubsequence(int[] arr) {
        int[] dp = new int[arr.length];
        int maxLen = 1;
        int maxLenIdx = 0;
        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxLenIdx = i;
            }
        }
        return generateSubsequence(arr, dp, maxLenIdx);
    }


    private static int[] generateSubsequence(int[] arr, int[] dp, int tail) {
        int[] res = new int[dp[tail]];
        res[res.length - 1] = arr[tail];
        int idx = res.length - 1;
        //第一次这里写错了，写成了i = res.length - 2
        for (int i = tail - 1; i >= 0; i--) {
            if (arr[i] < res[idx] && dp[i] == idx) {
                res[--idx] = arr[i];
            }
        }
        return res;
    }


    public static int[] longestIncreasingSubsequenceBetter(int[] arr) {
        int[] end = new int[arr.length];
        int[] dp = new int[arr.length];
        int right = -1;
        int maxLen = 1;
        int maxLenIdx = 0;
        for (int i = 0; i < arr.length; i++) {
            int l = 0;
            int r = right;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (end[mid] >= arr[i]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            right = Math.max(l, right);
            end[l] = arr[i];
            dp[i] = Math.max(1, l + 1);
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxLenIdx = i;
            }
        }
        return generateSubsequence(arr, dp, maxLenIdx);
    }
}
