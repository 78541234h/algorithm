package learn.algorithm.dynamic;

public class 最长公共子串 {
    public static void main(String[] args) {

    }

    public static String longestCommonSubString(String s1, String s2) {
        int m = 0;
        int n = s2.length() - 1;
        int maxLen = 0;
        int resEnd = -1;
        int curLen = 0;
        int num = s1.length() * s2.length();
        while (num-- > 0) {
            //比较字符
            if (s1.charAt(m) == s2.charAt(n)) {
                curLen++;
                if (curLen > maxLen) {
                    maxLen = curLen;
                    resEnd = m;
                }
            } else {
                curLen = 0;
            }
            //移动指针
            if (m == s1.length() - 1 || n == s2.length() - 1) {
                if(m < n) {
                    //第一次写，下面两个语句反了，导致n永远不变。
                    n = n - m - 1;
                    m = 0;
                } else {
                    m = m - n + 1;
                    n = 0;
                }
                curLen = 0;
            } else { //第一次这里忘写了
                m++;
                n++;
            }
        }
        //第一次写这里出错了，把substring里的resEnd写成了m
        return resEnd == -1 ? "" : s1.substring(resEnd - maxLen + 1, resEnd + 1);
    }}
