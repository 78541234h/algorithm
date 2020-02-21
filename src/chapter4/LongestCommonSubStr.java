package chapter4;

import utils.PrintUtil;

public class LongestCommonSubStr {
    public static void main(String[] args) {
        test();
    }

    public static boolean test() {
        String s1 = "1AB2345CD";
        String s2 = "12345EF";
        PrintUtil.print(longestCommonSubStr1(s1, s2));
        return true;
    }

    public static String longestCommonSubStr(String s1, String s2) {
        int[] dp = new int[s2.length()];
        for (int i = 0; i < s2.length(); i++) {
            dp[i] = s1.charAt(0) == s2.charAt(i) ? 1 : 0;
        }
        int max = 0;
        int index = 0;
        for (int i = 1; i < s1.length(); i++) {
            int last = dp[0];
            dp[0] = s1.charAt(i) == s2.charAt(0) ? 1 : 0;
            int cur;
            for (int j = 1; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    cur = dp[j];
                    dp[j] = last + 1;
                    last = cur;
                    if (dp[j] > max) {
                        max = dp[j];
                        index = i;
                    }
                } else
                    last = dp[j]; //第一遍忘了这里，忽略了情况
            }
        }
        return s1.substring(index - max + 1, index + 1);
    }


    public static String longestCommonSubStr1(String s1, String s2) {
        int startR = 0, startC = s2.length() - 1;
        int r, c;
        int len, index = 0, max = 0;
        while (startR < s1.length()) {
            len = s1.charAt(startR) == s2.charAt(startC) ? 1 : 0;
            r = startR;
            c = startC;
            while (++r < s1.length() && ++c < s2.length()) {
                if (s1.charAt(r) == s2.charAt(c)) {
                    len++;
                    if (max < len) {
                        index = r;
                        max = len;
                    }
                } else {
                    len = 0;
                }
            }
            if (startC > 0)
                --startC;
            else
                ++startR;
        }
        return s1.substring(index - max + 1, index + 1);
    }
}
