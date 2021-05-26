public class 旋变字符串问题 {
    public static void main(String[] args) {
        String s1 = "abcd";
        String s2 = "cadb";
        System.out.println(isScramble2(s1, s2));
    }


    public static boolean isScramble1(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;
        if (containsSameChar(s1, s2))
            return process(s1, s2, 0, 0, s1.length());
        return false;
    }


    private static boolean process(String s1, String s2, int start1, int start2, int len) {
        if (len == 1) {
            return s1.charAt(start1) == s2.charAt(start2);
        }

        for (int leftLen = 1; leftLen < len; leftLen++) {
            int rightLen = len - leftLen;
            if ((process(s1, s2, start1, start2, leftLen)
                    && process(s1, s2, start1 + leftLen, start2 + leftLen, rightLen))
                    || (process(s1, s2, start1, start2 + rightLen, leftLen)
                    && process(s1, s2, start1 + leftLen, start2, rightLen))) {
                return true;
            }
        }
        return false;
    }


    private static boolean containsSameChar(String s1, String s2) {
        int[] map = new int[256];
        int len = s1.length();
        for (int i = 0; i < len; i++) {
            map[s1.charAt(i)]++;
        }
        for (int i = 0; i < len; i++) {
            if (map[s2.charAt(i)]-- == 0) {
                return false;
            }
        }
        return true;
    }


    public static boolean isScramble2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;
        if (!containsSameChar(s1, s2)) return false;
        int len = s1.length();
        boolean[][][] dp = new boolean[len + 1][len][len];
        for (int i = 0; i < len; i++) {
            char c1 = s1.charAt(i);
            for (int j = 0; j < len; j++) {
                char c2 = s2.charAt(j);
                if (c1 == c2) dp[1][i][j] = true;
            }
        }
        for (int k = 2; k <= len; k++) {
            for (int i = 0; i <= len - k; i++) {
                for (int j = 0; j <= len - k; j++) {
                    for (int leftLen = 1; leftLen < k; leftLen++) {
                        int rightLen = k - leftLen;
                        boolean isScramble =
                                (dp[leftLen][i][j] && dp[rightLen][i + leftLen][j + leftLen]) ||
                                        (dp[leftLen][i][j + rightLen] && dp[rightLen][i + leftLen][j]);
                        if (isScramble) {
                            dp[k][i][j] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[len][0][0];
    }
}
