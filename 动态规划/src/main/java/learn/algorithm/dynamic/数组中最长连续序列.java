package learn.algorithm.dynamic;

import java.util.HashMap;

public class 数组中最长连续序列 {
    public static void main(String[] args) {
        int[] arr = {100, 4, 200, 1, 3, 2};
        System.out.println(longestContiguousSequence(arr));
    }


    public static int longestContiguousSequence(int[] arr) {
        int maxLen = 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], arr[i]);
                if (map.containsKey(arr[i] - 1)) {
                    maxLen = Math.max(maxLen, merge(map, map.get(arr[i] - 1), arr[i]));
                }
                if (map.containsKey(arr[i] + 1)) {
                    maxLen = Math.max(maxLen, merge(map, map.get(arr[i]), map.get(arr[i] + 1)));
                }
            }
        }
        return maxLen;
    }

    //small表示左区间的最左端，big表示右区间的最右端
    private static int merge(HashMap<Integer, Integer> map, int small, int big) {
        int len = big - small + 1;
        map.put(small, big);
        map.put(big, small);
        return len;
    }

}
