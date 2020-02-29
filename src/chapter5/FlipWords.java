package chapter5;

import utils.PrintUtil;

public class FlipWords {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        String s = "dog loves pig";
        char[] chas = s.toCharArray();
        flip(chas);
        PrintUtil.print(chas);
    }

    public static void flip(char[] chas) {
        int left = 0, right = 0;
        for (int i = 0; i < chas.length; i++) {
            if (chas[i] != ' ') {
                if (i == 0 || chas[i - 1] == ' ')
                    left = i;
                if (i == chas.length - 1 || chas[i + 1] == ' ') {
                    right = i;
                    reverse(chas, left, right);
                }
            }
        }
        reverse(chas, 0, chas.length - 1);
    }

    public static void flip(char[] chas, int size) {
        if (chas == null || size < 1 || size >= chas.length) return;
        reverse(chas, 0, size - 1);
        reverse(chas, size, chas.length - 1);
        reverse(chas, 0, chas.length - 1);
    }

    private static void reverse(char[] chas, int left, int right) {
        while (left < right) {
            char tmp = chas[left];
            chas[left++] = chas[right];
            chas[right--] = tmp;
        }
    }


}
