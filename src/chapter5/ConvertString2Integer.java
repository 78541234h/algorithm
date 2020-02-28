package chapter5;

public class ConvertString2Integer {
    public static void main(String[] args) {
    }

    public static void test() {

    }

    public static Integer convert(String str) {
        if (str == null || str.length() < 1) return 0;
        if (!isValid(str)) return 0;
        int minq = Integer.MIN_VALUE / 10;
        int minr = Integer.MIN_VALUE % 10;
        boolean posi = true;
        int res = 0;
        int i = 0;
        if (str.charAt(i) == '-') {
            i++;
            posi = false;
        }
        for (; i < str.length(); i++) {
            int val = '0' - str.charAt(i);
            if (res < minq) return 0;
            if (res == minq && val < minr) return 0;
            res = res * 10 - (str.charAt(i) - '0');
        }
        if (posi) {
            if (res == Integer.MIN_VALUE)
                return 0;
            res = -res;
        }
        return res;
    }

    public static boolean isValid(String str) {
        if (str.charAt(0) != '-' && (str.charAt(0) < '0' || str.charAt(0) > '9')) return false;
        if (str.charAt(0) == '-' && (str.length() == 1 || str.charAt(1) == 0)) return false;
        if (str.charAt(0) == '0' && str.length() > 1) return false;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) > '9' || str.charAt(i) < '0')
                return false;
        }
        return true;
    }
}
