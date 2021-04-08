package chapter5;

import utils.PrintUtil;

public class FlipWords {
    public static void main(String[] args) {
        test2();
    }

    public static void test() {
        String s = "dog loves pig";
        char[] chas = s.toCharArray();
        flip(chas);
        PrintUtil.print(chas);
    }

    public static void test2() {
        String s = "abcd123";
        char[] chas = s.toCharArray();
        flipBetter(chas, 4);
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

    public static void flip2(char[] chas, int size) {
        if (chas == null || size < 1 || size >= chas.length) return;
        int left = 0, right = chas.length - 1;
        while (left < right) {
            if (size * 2 < (right - left + 1)) {
                exchange(chas, left, right, size);
                right = right - size;
            } else if (size * 2 > (right - left + 1)) {
                exchange(chas, left, right, right - left - size + 1);
                int tmp = left;
                left = left + right - size + 1;
                size = size - (right - tmp + 1 - size);
            } else {
                exchange(chas, left, right, size);
                break;
            }
        }
    }

    public static void flipBetter(char[] chas, int size) {
        int lpart = size, rpart = chas.length - size;
        int start = 0, end = chas.length - 1;
        while (true) {
            int d = lpart - rpart;
            int s = Math.min(lpart, rpart);
            exchange(chas, start, end, s);
            if (d > 0) {
                start = start + s;
                lpart = d;
                //rpart = s;　// 没必要加上这一句
            } else if (d < 0) {
                end = end - s;
                rpart = rpart - s;
                //lpart = s;  // 没必要加上这一句
            } else {
                break;
            }
        }
    }

    private static void exchange(char[] chas, int left, int right, int num) {
        right = right - num + 1;
        while (--num >= 0) {
            char tmp = chas[left];
            chas[left++] = chas[right];
            chas[right++] = tmp;
        }
    }
}
