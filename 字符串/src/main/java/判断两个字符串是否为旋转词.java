public class 判断两个字符串是否为旋转词 {
    public static void main(String[] args) {

    }

    public static boolean isRotated(String a, String b) {
        if (a == null || b == null || a.length() != b.length()) return false;
        String bb = b + b;
        return bb.contains(a);
    }
}
