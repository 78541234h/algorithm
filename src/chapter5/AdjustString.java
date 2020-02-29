package chapter5;

public class AdjustString {
    public static void main(String[] args) {

    }

    public static void test() {

    }

    //
    public static void adjust1(char[] chas) {
        if (chas == null || chas.length == 0) return;
        int last = chas.length - 1;
        int spaceNum = 0;
        for (int i = 0; i < chas.length; i++) {
            if (chas[i] == ' ') spaceNum++;
            else last = i;
        }
        int len = spaceNum * 2 + last;
        for (; last >= 0; last--) {
            if (chas[last] == ' ') {
                chas[len--] = '0';
                chas[len--] = '2';
                chas[len--] = '%';
            } else {
                chas[len--] = chas[last];
            }
        }
    }

    public static void adjust2(char[] chas) {
        int pos = chas.length - 1;
        for (int i = chas.length - 1; i >= 0; i--) {
            if (chas[i] != '*')
                chas[pos--] = chas[i];
        }
        while (pos >= 0) chas[pos--] = '*';
    }
}
