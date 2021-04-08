package chapter5;

import utils.PrintUtil;

public class StringMatchProblem {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        String str = "acd";
        String exp = "a*d";
        //PrintUtil.print(match(str, exp));
        PrintUtil.print(matchByDP(str, exp));
    }

    public static boolean match(String str, String exp) {
        if (str == null || exp == null || (str.length() > 0 && exp.length() == 0)) return false;
        return process(str, exp, 0, 0);
    }

    private static boolean process(String str, String exp, int si, int ei) {
        if (ei == exp.length())
            return si == str.length();
        if (ei == exp.length() - 1 || exp.charAt(ei + 1) != '*') {
            return si != str.length() &&
                    (str.charAt(si) == exp.charAt(ei) || exp.charAt(ei) == '.') &&
                    process(str, exp, si + 1, ei + 1);
        }
        while (si < str.length() && (str.charAt(si) == exp.charAt(ei) || exp.charAt(ei) == '.')) {
            if (process(str, exp, si, ei + 2)) {
                return true;
            }
            si++;
        }
        return process(str, exp, si, ei + 2);
    }

    public static boolean matchByDP(String str, String exp) {
        boolean[][] dp = new boolean[str.length() + 1][exp.length() + 1];
        dp[str.length()][exp.length()] = true;
        for (int i = str.length() - 1; i > -1; i--) {
            for (int j = exp.length() - 1; j > -1; j--) {
                if (exp.charAt(j) != '*') {
                    if (j == exp.length() - 1 || (exp.charAt(j + 1) != '*')) {
                        dp[i][j] = (str.charAt(i) == exp.charAt(j) || exp.charAt(j) == '.')
                                && dp[i + 1][j + 1];
                    } else {
                        int si = i;
                        while (si < str.length() &&
                                (exp.charAt(j) == '.' || exp.charAt(j) == str.charAt(si))) {
                            dp[i][j] = dp[i][j] || dp[si++][j + 2];
                        }
                        dp[i][j] = dp[i][j] || dp[si][j + 2];

                    }
                }
            }
        }
        return dp[0][0];
    }

}
