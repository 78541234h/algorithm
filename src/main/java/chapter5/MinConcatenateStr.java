package chapter5;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class MinConcatenateStr {
    public static void main(String[] args) {

    }

    public static void test() {

    }

    public static String minConcatenateStr(String[] arr) {
        if (arr == null || arr.length == 0) return null;
        if (arr.length == 1) return arr[0];
        StringBuilder builder = new StringBuilder();
        Arrays.sort(arr, new MyComparator());
        for (String str : arr) {
            builder.append(str);
        }
        return builder.toString();
    }

    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }


}
