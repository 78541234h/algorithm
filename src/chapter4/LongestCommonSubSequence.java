package chapter4;

import utils.PrintUtil;

import java.util.Vector;

public class LongestCommonSubSequence {
    public static void main(String[] args) {
        test();
    }

    public static boolean test() {
        String s1 = "1A2C3D4B56";
        String s2 = "B1D23CA45B6A";
        PrintUtil.print(longestCommonSubSeq(s1, s2));
        return true;
    }

    public static String longestCommonSubSeq(String s1, String s2) {
        int[][] dp = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(0)) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = i == 0 ? 0 : dp[i - 1][0];
            }
        }
        for (int i = 0; i < s2.length(); i++) {
            if (s2.charAt(i) == s1.charAt(0)) {
                dp[0][i] = 1;
            } else {
                dp[0][i] = i == 0 ? 0 : dp[0][i - 1];
            }
        }
        for (int i = 1; i < s1.length(); i++) {
            for (int j = 1; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
            }
        }
        int len = dp[s1.length() - 1][s2.length() - 1];
        char[] res = new char[len];
        int row = s1.length() - 1;
        int col = s2.length() - 1;
        while (len > 0) {
            if (row > 0 && dp[row][col] == dp[row - 1][col]) {
                --row;
            } else if (col > 0 && dp[row][col] == dp[row][col - 1]) {
                --col;
            } else {
                res[--len] = s1.charAt(row);
                --row;
                --col;
            }
        }
        return String.valueOf(res);
    }
}
