package chapter5;

public class ParenthesizedStrValidityProblem {
    public static void main(String[] args) {

    }

    public static void test() {

    }

    public static boolean isValidity(String str) {
        if (str == null || str.length() == 0 || str.length() % 2 != 0) return false;
        int record = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') ++record;
            else if (c == ')' && --record < 0) return false;
            if (c != '(' && c != ')') return false;
        }

        /*Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(')
                stack.push('(');
            else if (str.charAt(i) == ')') {
                if (stack.isEmpty() || stack.peek() != '(')
                    return false;
                stack.pop();
            } else
                return false;
        }
        if (!stack.empty()) return false;*/
        return record == 0;
    }

    public static int longestValidityParenthesizedStr(String str) {
        int[] dp = new int[str.length()];
        int res = 0;
        for (int i = 1; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ')' && dp[i - 1] != 0) {
                int match = i - dp[i - 1] - 1;
                if (match >= 0 && str.charAt(match) == '(')
                    dp[i] = 2 + dp[i - 1];
                if (match > 0)
                    dp[i] += dp[match - 1];
                res = Math.max(res, dp[i]);
            }
        }
        return res;
    }
}
