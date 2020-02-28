package chapter5;

public class JudgeDeformedWord {
    public static void main(String[] args) {

    }

    public static boolean test() {
        return false;
    }

    public static boolean isDeformedWords(String s1, String s2) {
        if ((s1 == null || s1.length() == 0) && (s2 == null || s2.length() == 0)) return true;
        else if (s1 != null && s2 != null && s1.length() == s2.length()) {
            int[] map = new int[256];
            for (int i = 0; i < s1.length(); i++) {
                map[s1.charAt(i)]++;
            }
            for (int j = 0; j < s1.length(); j++) {
                if (map[s2.charAt(j)]-- == 0)
                    return false;
            }
            return true;
        } else {
            return false;
        }
    }
}