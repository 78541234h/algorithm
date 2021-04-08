package chapter5;

public class KMP {
    public static void main(String[] args) {

    }

    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length())
            return -1;
        int i = 0, j = 0;
        int[] next = getNextArr(m);
        while (i < s.length() && j < m.length()) {
            if (s.charAt(i) == m.charAt(j)) {
                i++;
                j++;
            } else if (next[j] == -1) {
                i++;
            } else {
                j = next[j];
            }
        }
        return j == m.length() ? i - j : -1;
    }

    private static int[] getNextArr(String s) { //the method will never be called from outside, so check is useless
        if (s.length() == 1) {
            return new int[]{-1};
        }
        int[] res = new int[s.length()];
        res[0] = -1;
        res[1] = 0;
        int i = 2, j = 0;
        while (i < s.length()) {
            if (s.charAt(i - 1) == s.charAt(j))
                res[i++] = ++j;
            else if (j > 0) {
                j = res[j];
            } else {
                res[i++] = 0;
                j = 0;
            }
        }
        return res;
    }
}