public class 判断两个词是否为变形词 {

    public static void main(String[] args) {

    }

    public static boolean isDeformation(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) return false;
        char[] chas1 = s1.toCharArray();
        char[] chas2 = s2.toCharArray();
        int[] map = new int[256];
        for (int i = 0; i < s1.length(); i++) {
            map[chas1[i]]++;
        }
        for (int i = 0; i < s1.length(); i++) {
            if (map[chas2[i]]-- == 0)
                return false;
        }
        return true;
    }
}
