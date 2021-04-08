package chapter5;

import utils.PrintUtil;

public class FindStrInMayNullStrArr {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        String[] arr = {null, "a", null, "a", null, "b", null, "c"};
        PrintUtil.print(findPos(arr, "a"));
    }

    public static int findPos(String[] arr, String s) {
        int left = 0;
        int res = -1;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] != null) {
                if (arr[mid].equals(s)) {
                    res = mid;
                    right = mid - 1;
                } else if (arr[mid].compareTo(s) > 0) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                int i = mid;
                while (--i >= left && arr[i] == null) ;
                if (i < left || arr[i].compareTo(s) < 0) {
                    left = mid + 1;
                } else {
                    res = s.equals(arr[i]) ? i : res;
                    right = i - 1;
                }
            }
        }
        return res;
    }
}
