package chapter5;

import utils.PrintUtil;

public class FindNewTypeChar {
    public static void main(String[] args) {
        test();
    }


    public static void test() {
        String str = "aaABCDEcBCg";
        PrintUtil.print(newChar(str, 7));
    }

    public static String newChar(String str, int pos) {
        if (str == null || pos >= str.length() || pos < 0) return "";
        int cur = 0;
        while (true) {
            if (str.charAt(cur) >= 'a' && str.charAt(cur) <= 'z') {
                if (cur == pos) return String.valueOf(cur);
                cur++;
            } else {
                if (cur + 2 > pos) return str.substring(cur, cur + 2);
                cur += 2;
            }
        }
    }
}
