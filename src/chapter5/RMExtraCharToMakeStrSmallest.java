package chapter5;

import utils.PrintUtil;

public class RMExtraCharToMakeStrSmallest {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        String s = "dbcacbca";
        PrintUtil.print(rmExtraChar(s));
    }

    public static String rmExtraChar(String s) {
        int L = 0, R = 0, len = 0;
        char[] res = new char[s.length()];
        int[] map = new int[26];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
        }
        while (R < s.length()) {
            if (map[s.charAt(R) - 'a'] == -1 || --map[s.charAt(R) - 'a'] > 0) {
                R++;
            } else {
                int pick = L;
                for (int i = L + 1; i <= R; i++) {
                    if (map[s.charAt(i) - 'a'] != -1 && s.charAt(i) < s.charAt(pick))
                        pick = i;
                }
                res[len++] = s.charAt(pick);
                map[s.charAt(pick) - 'a'] = -1;
                for (int i = pick + 1; i <= R; i++) {
                    if (map[s.charAt(i) - 'a'] != -1)
                        map[s.charAt(i) - 'a']++;
                }
                L = pick + 1;
                R = pick + 1;
            }
        }
        return String.valueOf(res);
    }
}
