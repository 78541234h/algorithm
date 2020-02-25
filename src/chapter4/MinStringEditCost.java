package chapter4;

import utils.PrintUtil;

public class MinStringEditCost {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        PrintUtil.print(minStrEditCostDP("abc", "adc", 5, 3, 100));
    }


    public static int minStrEditCost(String s1, String s2, int ic, int dc, int rc) {
        return processRecur(s1, s2, s1.length() - 1, s2.length() - 1, ic, dc, rc);
    }

    public static int processRecur(String s1, String s2, int end1, int end2, int ic, int dc, int rc) {
        if (end1 == -1) {
            return ic * (end2 - end1);
        } else if (end2 == -1) {
            return dc * (end1 - end2);
        }
        int cost;
        if (s1.charAt(end1) == s2.charAt(end2)) {
            cost = processRecur(s1, s2, end1 - 1, end2 - 1, ic, dc, rc);
        } else {
            cost = processRecur(s1, s2, end1 - 1, end2 - 1, ic, dc, rc) + Math.min(rc, dc + ic);
        }
        cost = Math.min(cost, processRecur(s1, s2, end1 - 1, end2, ic, dc, rc) + dc);
        cost = Math.min(cost, processRecur(s1, s2, end1, end2 - 1, ic, dc, rc) + ic);
        return cost;
    }

    public static int minStrEditCostDP(String s1, String s2, int ic, int dc, int rc) {
        int[] dp = new int[s2.length() + 1];
        for (int i = 0; i <= s2.length(); i++) {
            dp[i] = ic * i;
        }
        for (int i = 1; i <= s1.length(); i++) {
            int tmp1 = dp[0];
            dp[0] = dc * i;
            for (int j = 1; j <= s2.length(); j++) {
                int tmp2 = dp[j];
                dp[j] = tmp1;
                if (s1.charAt(i - 1) != s2.charAt(j - 1))
                    dp[j] += Math.min(rc, ic + dc);
                dp[j] = Math.min(dp[j], tmp2 + dc);
                dp[j] = Math.min(dp[j], dp[j - 1] + ic);
                tmp1 = tmp2;
            }
        }
        return dp[s2.length()];
    }

}
