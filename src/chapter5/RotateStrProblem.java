package chapter5;

import utils.PrintUtil;

public class RotateStrProblem {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        String s1 = "abcd";
        String s2 = "acbd";
        PrintUtil.print(isRotatedStr(s1, s2));
        PrintUtil.print(isRotatedStrDP(s1, s2));
    }

    public static boolean isRotatedStr(String s1, String s2) {
        return process(s1, s2, 0, 0, s1.length());
    }

    private static boolean process(String s1, String s2, int L1, int L2, int size) {
        if (size == 1)
            return s1.charAt(L1) == s2.charAt(L2);
        for (int leftPart = 1; leftPart < size; leftPart++) {
            if ((process(s1, s2, L1, L2, leftPart) &&
                    process(s1, s2, L1 + leftPart, L2 + leftPart, size - leftPart)) ||
                    (process(s1, s2, L1, L2 + size - leftPart, leftPart) &&
                            process(s1, s2, L1 + leftPart, L2, size - leftPart))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isRotatedStrDP(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) return false;
        boolean[][][] dp = new boolean[s1.length()][s2.length()][s1.length() + 1];
        for (int size = 1; size <= s1.length(); size++) {
            for (int L1 = 0; L1 < s1.length() - size + 1; L1++) {
                for (int L2 = 0; L2 < s1.length() - size + 1; L2++) {
                    dp[L1][L2][1] = s1.charAt(L1) == s2.charAt(L2);
                    for (int leftPart = 1; leftPart < size; leftPart++) {
                        if ((dp[L1][L2][leftPart] &&
                                dp[L1 + leftPart][L2 + leftPart][size - leftPart])
                                || (dp[L1][L2 + size - leftPart][leftPart] &&
                                dp[L1 + leftPart][L2][size - leftPart])) {
                            dp[L1][L2][size] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[0][0][s1.length()];
    }


}
