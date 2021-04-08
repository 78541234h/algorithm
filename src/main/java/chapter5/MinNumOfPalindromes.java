package chapter5;

import utils.PrintUtil;

public class MinNumOfPalindromes {
    public static void main(String[] args) {
        test();
    }

    public static int minCut(String str) {
        int[] dp = new int[str.length() + 1];
        dp[str.length()] = -1;
        boolean[] record = new boolean[str.length()];
        for (int i = str.length() - 1; i > -1; i--) {
            dp[i] = Integer.MAX_VALUE;
            boolean tmp1 = false, tmp2;
            for (int j = i; j < str.length(); j++) {
                tmp2 = record[j];
                if (str.charAt(i) == str.charAt(j) && (j - i < 2 || tmp1)) {
                    record[j] = true;
                    dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                } else {
                    record[j] = false;
                }
                tmp1 = tmp2;

            }
        }
        return dp[0];
    }

    public static boolean test() {
        String s = "ACDCDCDAD";
        PrintUtil.print(minCut(s));
        assert (minCut(s) == 2);
        return true;
    }
}
