public class 将整数字符串转成整数值 {
    public static void main(String[] args) {
        String[] strs = {"123", "0123", "A12", "0", "2147483648", "-2147483649", "-123"};
        for (String str : strs) {
            System.out.println(convert(str));
        }
    }


    public static int convert(String str) {
        if (str == null || str.length() == 0) return 0;
        char[] chas = str.toCharArray();
        int cur = 0;
        int len = chas.length;
        if (chas[0] == '-') {
            cur = 1;
            if (len == 1 || chas[1] == '0' || len > 11) return 0;
        } else if (chas[0] > '9' || chas[0] < '0') {
            return 0;
        } else {
            if (len > 10) return 0;
            if (len > 1 && chas[0] == '0')
                return 0;
        }
        long res = 0;
        while (cur < len) {
            if (chas[cur] > '9' || chas[cur] < '0')
                return 0;
            res = res * 10 + (chas[cur] - '0');
            cur++;
        }
        if (chas[0] == '-') {
            res = -res;
            if (res < Integer.MIN_VALUE) return 0;
        } else if (res > Integer.MAX_VALUE) {
            return 0;
        }
        return (int) res;
    }
}
