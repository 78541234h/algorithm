package chapter5;

import utils.PrintUtil;

public class MinInclusionSubString {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        String s1 = "adabbca";
        String s2 = "acb";
        PrintUtil.print(minInclusionSubString(s1, s2));
        s1 = "12345";
        s2 = "344";
        PrintUtil.print(minInclusionSubString(s1, s2));
    }

    public static Integer minInclusionSubString(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < s2.length() || s2.length() == 0) return 0;
        if (s1.equals(s2)) return s1.length();
        int start = 0, end = -1;
        int s2len = s2.length();
        int res = Integer.MAX_VALUE;
        int[] map = new int[256];
        for (int i = 0; i < s2.length(); i++) {
            map[s2.charAt(i)] += 1;
        }
        while (true) {
            if (s2len > 0) {
                if (end == s1.length() - 1)
                    break;
                end++;
                if (map[s1.charAt(end)] > 0) s2len--;
                map[s1.charAt(end)]--;
            } else {
                if (map[s1.charAt(start)] >= 0) {
                    s2len++;
                    res = Math.min(res, end - start + 1);
                }
                map[s1.charAt(start++)]++;
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
