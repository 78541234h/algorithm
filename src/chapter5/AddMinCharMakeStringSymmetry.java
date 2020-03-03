package chapter5;

import utils.PrintUtil;

public class AddMinCharMakeStringSymmetry {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        String ori = "A1BC22DE1F";
        PrintUtil.print(minSymmetryString(ori));
    }

    public static String minSymmetryString(String ori) {
        int[][] dp = new int[ori.length()][ori.length()];
        for (int i = ori.length() - 2; i >= 0; i--) {
            dp[i][i + 1] = ori.charAt(i) == ori.charAt(i + 1) ? 0 : 1;
            for (int j = i + 2; j < ori.length(); j++) {
                if (ori.charAt(i) == ori.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1];
                else dp[i][j] = Math.min(dp[i][j - 1], dp[i + 1][j]) + 1;
            }
        }
        char[] res = new char[dp[0][ori.length() - 1] + ori.length()]; // 第一次写，这里忘了dp的含义是添加字符的数量，误以为是总数量
        int s = 0, e = res.length - 1;
        int l = 0, r = ori.length() - 1;
        while (l <= r) {
            if (ori.charAt(l) == ori.charAt(r)) {
                res[s++] = res[e--] = ori.charAt(l);
                l++;
                r--;
            } else if (dp[l][r] == dp[l + 1][r] + 1) {
                res[s++] = res[e--] = ori.charAt(l++);
            } else {
                res[s++] = res[e--] = ori.charAt(r--);
            }
        }
        return String.valueOf(res);
    }

}
