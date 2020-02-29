package chapter5;

import utils.PrintUtil;

public class JudgeUniqueCharInString {
    public static void main(String[] args) {
        test();

    }

    public static void test() {
        char[] arr = {'1', '6', '4', '2', '5', '8', '9', '7', '3'};
        PrintUtil.print(judgeCharUnique(arr));
    }

    //T(N)
    //扩展ASCII码有256个，ASCII有128个
    public static boolean judgeCharUnique(String s) {
        if (s == null || s.length() < 2) return true;
        boolean[] map = new boolean[256];
        for (int i = 0; i < s.length(); i++) {
            if (map[s.charAt(i)]) return false;
            map[s.charAt(i)] = true;
        }
        return true;
    }

    //T(nlogn) S(1)
    public static boolean judgeCharUnique(char[] arr) {
        if (arr == null || arr.length < 2) return true;
        heapsort(arr);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) return false;
        }
        return true;
    }

    private static void heapsort(char[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            shiftDown(arr, i, arr.length - 1);
        }
        for (int i = arr.length - 1; i > 0; i--) {
            char tmp = arr[i];
            arr[i] = arr[0];
            arr[0] = tmp;
            shiftDown(arr, 0, i - 1);
        }
    }

    private static void shiftDown(char[] arr, int from, int lower) {
        int lc = from * 2 + 1;
        char tmp = arr[from];
        while (lc <= lower) {
            if (lc + 1 <= lower && arr[lc] > arr[lc + 1])
                lc = lc + 1;
            if (tmp < arr[lc]) break;
            arr[from] = arr[lc];
            from = lc;
            lc = from * 2 + 1;
        }
        arr[from] = tmp;

    }


    public static int KMP(String sl, String ss) {
        if (ss == null || ss.length() == 0) return 0;
        if (sl == null || sl.length() == 0) return -1;
        int[] help = help(ss);
        int i = 0, j = 0;
        while (i < sl.length() && j < ss.length()) {
            if (sl.charAt(i) == ss.charAt(j)) {
                i++;
                j++;
            } else if (j == 0) {
                i++;
            } else {
                j = help[j];
            }
        }
        return j == ss.length() ? i - j : -1;
    }

    private static int[] help(String s) {
        int[] help = new int[s.length()];
        help[0] = -1;
        if (s.length() > 2) {
            int pos = 2;
            int cn = 0;
            while (pos < s.length()) {
                if (s.charAt(pos - 1) == s.charAt(cn)) {
                    help[pos++] = ++cn;
                } else if (cn == 0) {
                    help[pos++] = 0;
                } else {
                    cn = help[cn];
                }
            }
        }
        return help;
    }
}
