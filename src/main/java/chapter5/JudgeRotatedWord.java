package chapter5;

public class JudgeRotatedWord {
    public static void main(String[] args) {

    }

    public static void test() {
    }


    public static boolean isRotatedWord(String s1, String s2) {
        if ((s1 == null || s1.length() == 0) && (s2 == null || s2.length() == 0)) return true;
        else if (s1 != null && s2 != null && s1.length() == s2.length()) {
            String tmp = s2 + s2;
            return tmp.contains(s1);
        } else {
            return false;
        }
    }
}
