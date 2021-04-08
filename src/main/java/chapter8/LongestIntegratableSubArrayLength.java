package chapter8;

import java.util.HashSet;

public class LongestIntegratableSubArrayLength {
    public static void main(String[] args) {

    }

    public static int getLength(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int res = Integer.MIN_VALUE;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length - 1; i++) {
            int min = Integer.MIN_VALUE;
            int max = Integer.MAX_VALUE;
            for (int j = i; j < arr.length; j++) {
                if (set.contains(arr[j])) break;
                set.add(arr[j]);
                max = Math.max(max, arr[j]);
                min = Math.min(min, arr[j]);
                if (max - min == j - i)
                    res = Math.max(res, j - i + 1);
            }
            set.clear();
        }
        return res;
    }

}
