package chapter4;

import utils.PrintUtil;

public class StrInterleavedComposition {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        PrintUtil.print(check("AB", "12", "BA12"));
    }

    public static boolean check(String s1, String s2, String aim) {
        return check(s1, s2, aim, s1.length() - 1, s2.length() - 1);
    }

    private static boolean check(String s1, String s2, String aim, int end1, int end2) {
        if (end1 == -1 && end2 == -1) return true;
        else if (end1 == -1) {
            if (aim.charAt(end2) == s2.charAt(end2))
                return check(s1, s2, aim, end1, end2 - 1);
            return false;
        } else if (end2 == -1) {
            if (aim.charAt(end1) == s1.charAt(end1))
                return check(s1, s2, aim, end1 - 1, end2);
            return false;
        } else {
            if (aim.charAt(end1 + end2 + 1) == s1.charAt(end1) && check(s1, s2, aim, end1 - 1, end2))
                return true;
            else return aim.charAt(end1 + end2 + 1) == s2.charAt(end2) && check(s1, s2, aim, end1, end2 - 1);
        }
    }

    public static boolean checkByDP(String s1, String s2, String aim) {
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= s1.length(); i++) {
            if (s1.charAt(i - 1) == aim.charAt(i - 1))
                dp[i][0] = true;
            else break;
        }
        for (int i = 1; i <= s2.length(); i++) {
            if (s2.charAt(i - 1) == aim.charAt(i - 1))
                dp[0][i] = true;
            else break;
        }
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                dp[i][j] = (s1.charAt(i - 1) == aim.charAt(i + j - 1) && dp[i - 1][j]) ||
                        (s2.charAt(j - 1) == aim.charAt(i + j - 1) && dp[i][j - 1]);
            }
        }
        return dp[s1.length()][s2.length()];
    }
}
