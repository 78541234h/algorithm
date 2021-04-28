package learn.algorithm.dynamic;

public class 最长回文子串 {


    public static String longestPalindrome(String s) {
        int len = s.length();
        int maxLen = 1;
        int resEnd = 0;
        int[][] dp = new int[len][len];
        for(int i = len - 1; i >= 0; i++) {
            dp[i][i] = 1;
            if(i + 1 < len) {
                dp[i][i+1] = s.charAt(i) == s.charAt(i+1) ? 2:0;
                if(dp[i][i+1] > maxLen) {
                    maxLen = dp[i][i+1];
                    resEnd = i+1;
                }
            }
            for(int j = i+2; j < len; j++) {
                if(s.charAt(i) == s.charAt(j) && dp[i+1][j-1] > 0) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                    if(dp[i][j] > maxLen) {
                        maxLen = dp[i][j];
                        resEnd = j;
                    }
                }
            }
        }
        return s.substring(resEnd-maxLen+1, resEnd + 1);
    }
}
