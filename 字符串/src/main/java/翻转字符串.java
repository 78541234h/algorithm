public class 翻转字符串 {
    public static void main(String[] args) {
        String s = "I’m a student.";
        System.out.println(reverseWords(s));


        String s1 = "12345678";
        char[] chas = s1.toCharArray();
        rotate2(chas, 2, chas.length - 1, 2);
        System.out.println(String.valueOf(chas));
    }

    public static String reverseWords(String str) {
        if (str == null || str.length() == 0) return null;
        char[] chas = str.toCharArray();
        reverse(chas, 0, chas.length - 1);
        int start = 0;
        char pre = str.charAt(0);
        for (int i = 1; i < str.length(); i++) {
            char cur = chas[i];
            if (cur != ' ' && pre == ' ') {
                start = i;
            }
            if (cur == ' ' && pre != ' ') {
                reverse(chas, start, i - 1);
            }
            pre = cur;
        }
        if (chas[chas.length - 1] != ' ')
            reverse(chas, start, chas.length - 1);
        return String.valueOf(chas);

    }

    private static void reverse(char[] chas, int start, int end) {
        if (start >= end) return;
        int half = (end - start + 1) / 2;
        for (int i = 0; i < half; i++) {
            swap(chas, start + i, end - i);
        }
    }

    private static void swap(char[] chas, int i, int j) {
        char tmp = chas[i];
        chas[i] = chas[j];
        chas[j] = tmp;
    }


    public static void rotate1(int[] arr, int leftLen) {
        if (arr == null || arr.length < 2) return;
        reverse(arr, 0, leftLen - 1);
        reverse(arr, leftLen, arr.length - 1);
        reverse(arr, 0, arr.length - 1);
    }

    private static void reverse(int[] arr, int start, int end) {
        if (start >= end) return;
        int halfLen = (end - start + 1) / 2;
        for (int i = 0; i < halfLen; i++) {
            swap(arr, start + i, end - i);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void rotate2(char[] arr, int l, int r, int leftLen) {
        int start = l;
        int end = r;
        int rightLen = end - start - leftLen + 1;
        while (true) {
            exchange(arr, start, end, Math.min(leftLen, rightLen));
            if (leftLen > rightLen) {
                start = start + rightLen;
                leftLen = leftLen - rightLen;
            } else if (leftLen < rightLen) {
                end = end - leftLen;
                rightLen = rightLen - leftLen;
            } else {
                break;
            }
        }
    }


    public static void exchange(char[] arr, int start, int end, int size) {
        int i = end - size + 1;
        while (size-- != 0) {
            char tmp = arr[start];
            arr[start] = arr[i];
            arr[i] = tmp;
            start++;
            i++;
        }
    }
}
