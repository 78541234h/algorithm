package learn.algorithm.dynamic.leetcode;

public class 正则表达式匹配 {
    public static void main(String[] args) {
        String s = "ab";
        String p = ".*";
        System.out.println(isMatchRecursive(s, p));
    }

    public static boolean isMatchRecursive(String s, String p) {
        return process(s, p, 0, 0);
    }

    private static boolean process(String s, String p, int sIdx, int pIdx) {
        if (sIdx == s.length() && pIdx == p.length())
            return true;
        if (pIdx == p.length())
            return false;
        //第一次写这里出错了，sIdx == s.length()并不能说明结果为false，因为p剩下的可能是a*等
        //if (sIdx == s.length())
        //    return false;
        //纠正了上面那个错误后，下面这条语句没有更正，导致越界。
        //char sc = s.charAt(sIdx);
        char pc = p.charAt(pIdx);
        if (pIdx < p.length() - 1 && p.charAt(pIdx + 1) == '*') {
            if (process(s, p, sIdx, pIdx + 2))
                return true;
            //第一次写以为.*匹配不了ab，一时记错了。
            while (sIdx < s.length() && (s.charAt(sIdx) == pc || pc == '.')) {
                if (process(s, p, ++sIdx, pIdx + 2)) {
                    return true;
                }
            }
            return false;
        } else {
            if (pc == '.' || (sIdx < s.length() && s.charAt(sIdx) == pc)) {
                return process(s, p, sIdx + 1, pIdx + 1);
            }
            return false;
        }
    }
}
