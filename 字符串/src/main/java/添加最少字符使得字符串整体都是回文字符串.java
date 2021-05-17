public class 添加最少字符使得字符串整体都是回文字符串 {
    public static void main(String[] args) {
        String s = "A1B21C";
        String strlps = "121";
        System.out.println(minStr(s, strlps));
    }

    public static String minStr(String str) {
        char[] chas = str.toCharArray();
        int len = chas.length;
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            if (i < len - 1 && chas[i] != chas[i + 1]) {
                dp[i][i + 1] = 1;
            }
            for (int j = i + 2; j < len; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                if (chas[i] == chas[j]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                }
            }
        }
        return getStr(chas, dp);
    }

    private static String getStr(char[] chas, int[][] dp) {
        int len = chas.length + dp[0][chas.length - 1];
        char[] res = new char[len];
        int i = 0;
        int j = chas.length - 1;
        int l = 0;
        int r = len - 1;
        while (l <= r) {
            if (dp[i][j] == dp[i + 1][j - 1] && chas[i] == chas[j]) {
                res[l++] = res[r--] = chas[i];
                i++;
                j--;
            } else if (dp[i][j] == dp[i][j - 1] + 1) {
                res[l++] = res[r--] = chas[j];
                j--;
            } else {
                res[l++] = res[r--] = chas[i];
                i++;
            }
        }
        return String.valueOf(res);
    }


    public static String minStr(String str, String strlps) {
        int len = 2 * str.length() - strlps.length();
        char[] res = new char[len];
        int cur = 0;
        int end = (strlps.length() - 1) / 2;
        int resIdx = 0;
        int l = 0;
        int r = str.length() - 1;

        while (cur <= end) {
            if (str.charAt(l) == strlps.charAt(cur) && str.charAt(r) == strlps.charAt(cur)) {
                res[resIdx] = str.charAt(l);
                res[len - resIdx - 1] = str.charAt(l);
                resIdx++;
                l++;
                r--;
                cur++;
            } else if (str.charAt(l) == strlps.charAt(cur)) {
                res[resIdx] = str.charAt(r);
                res[len - resIdx - 1] = str.charAt(r);
                resIdx++;
                r--;
            } else if (str.charAt(r) == strlps.charAt(cur)) {
                res[resIdx] = str.charAt(l);
                res[len - resIdx - 1] = str.charAt(l);
                resIdx++;
                l++;
            } else {
                res[resIdx] = str.charAt(l);
                res[len - resIdx - 1] = str.charAt(l);
                resIdx++;
                res[resIdx] = str.charAt(r);
                res[len - resIdx - 1] = str.charAt(r);
                resIdx++;
                l++;
                r--;
            }
        }
        return String.valueOf(res);
    }
}
