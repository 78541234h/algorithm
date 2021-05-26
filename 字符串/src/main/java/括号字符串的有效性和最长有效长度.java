public class 括号字符串的有效性和最长有效长度 {
    public static void main(String[] args) {
        String s = "()(()()(";
        System.out.println(maxValidLen(s));
    }

    public static boolean isValid(String str) {
        if (str == null || str.length() < 2) return false;
        if (str.length() % 2 == 1) return false;
        int leftCount = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != '(' && c != ')') {
                return false;
            } else if (c == '(') {
                leftCount++;
            } else {
                if (leftCount == 0) return false;
                leftCount--;
            }
        }
        return leftCount == 0;
    }

    public static int maxValidLen(String str) {
        if (str == null || str.length() < 2) return 0;
        int len = str.length();
        int[] dp = new int[len];
        int maxLen = 0;

        for (int i = 1; i < len; i++) {
            char c = str.charAt(i);
            if (c == ')') {
                int preIdx = i - 1 - dp[i - 1];
                if (preIdx >= 0 && str.charAt(preIdx) == '(') {
                    dp[i] = dp[i - 1] + 2;
                    if (preIdx > 0)
                        dp[i] += dp[preIdx - 1];
                    maxLen = Math.max(maxLen, dp[i]);
                }
            }
        }
        return maxLen;
    }


}
