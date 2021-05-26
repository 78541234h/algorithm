public class 找到指定的新类型字符 {
    public static void main(String[] args) {
        System.out.println(newTypeCharAt("aaABCDEcBCg", 10));

    }

    public static String newTypeCharAt(String str, int k) {
        if (str == null || str.length() < 1 || k < 0 || k >= str.length())
            throw new RuntimeException();
        int capitalCount = 0;
        for (int i = k - 1; i >= 0; i--) {
            char c = str.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                capitalCount++;
            } else
                break;
        }
        if ((capitalCount & 1) == 1) {
            return str.substring(k - 1, k + 1);
        }
        if (str.charAt(k) >= 'a' && str.charAt(k) <= 'z') {
            return str.substring(k, k + 1);
        }
        return str.substring(k, k + 2);
    }
}
