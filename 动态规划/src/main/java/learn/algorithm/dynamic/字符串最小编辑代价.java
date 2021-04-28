package learn.algorithm.dynamic;

public class 字符串最小编辑代价 {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "adc";
        System.out.print(minEditCost(s1,s2,5,3,100));

    }

    public static int minEditCost(String s1, String s2, int ic, int dc, int rc) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int j = 1; j <= s2.length(); j++) {
            dp[0][j] = ic * j;
        }
        for (int i = 1; i <= s1.length(); i++) {
            dp[i][0] = dc * i;
            for (int j = 1; j <= s2.length(); j++) {
                int cost = s1.charAt(i-1) == s2.charAt(j-1) ? dp[i - 1][j - 1] : dp[i - 1][j - 1] + rc;
                cost = Math.min(dp[i][j - 1] + ic, cost);
                cost = Math.min(dp[i - 1][j] + dc, cost);
                dp[i][j] = cost;
            }
        }
        return dp[s1.length()][s2.length()];
    }


}
