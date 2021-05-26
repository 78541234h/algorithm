import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class 拼接所有字符串产生字典序最小的大写字符串 {
    public static void main(String[] args) {
        String[] strs = {"ba", "b"};
        System.out.println(getString(strs));
    }

    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    public static class MyComparator2 implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            int end = Math.min(o1.length(), o2.length()) - 1;
            for (int i = 0; i <= end; i++) {
                if (o1.charAt(i) > o2.charAt(i)) {
                    return 1;
                } else if (o1.charAt(i) < o2.charAt(i)) {
                    return -1;
                }
            }
            if (o1.length() == o2.length()) return 0;
            int shortLen = 0;
            String longStr;
            if (o1.length() > o2.length()) {
                shortLen = o2.length();
                longStr = o1;
            } else {
                shortLen = o1.length();
                longStr = o2;
            }
            for (int i = shortLen; i < longStr.length(); i++) {
                if (longStr.charAt(i) > longStr.charAt(0))
                    return longStr == o1 ? 1 : -1;
                else if (longStr.charAt(i) < longStr.charAt(0))
                    return longStr == o1 ? -1 : 1;
            }
            return 0;
        }
    }


    public static String getString(String[] strs) {
        List<String> list = Arrays.asList(strs);
        list.sort(new MyComparator2());
        StringBuilder builder = new StringBuilder();
        for (String str : list) {
            builder.append(str);
        }
        return builder.toString();
    }
}
