package chapter4;

import utils.PrintUtil;

import java.util.LinkedList;
import java.util.List;

public class LongestIncreasingSubSequence {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        int[] arr = {2, 1, 5, 3, 3, 6, 4, 8, 9, 7};
        List<Integer> ls = longestIncreasingSubSeq2(arr);
        PrintUtil.print(ls);
    }

    public static List<Integer> longestIncreasingSubSeq2(int[] arr) {
        int[] ends = new int[arr.length];
        int[] dp = new int[arr.length];
        int boundary = -1;
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            int l = 0;
            int r = boundary;
            while (l <= r) {
                int m = (l + r) / 2;
                if (arr[i] > ends[m])
                    l = m + 1;
                else
                    r = m - 1;
            }
            dp[i] = l + 1;
            ends[l] = arr[i];
            if (l > boundary) {
                boundary = l;
                maxIndex = i;
            }
        }
        LinkedList<Integer> ls = new LinkedList<>();
        for (int i = maxIndex; i > 0; i--) {
            if (dp[i] == boundary + 1) {
                boundary--;
                ls.addFirst(arr[i]);
            }
        }
        return ls;
    }


    public static List<Integer> longestIncreasingSubSeq(int[] arr) {
        LinkedList<Integer> ls = new LinkedList<>();
        int[] dp = new int[arr.length];
        int maxLen = 0, index = 0;
        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (maxLen < dp[i]) {
                maxLen = dp[i];
                index = i;
            }
        }
        for (int i = index; i >= 0; i--) {
            if (dp[i] == maxLen) {
                maxLen--;
                ls.addFirst(arr[i]);
            }
        }
        return ls;
    }

}
