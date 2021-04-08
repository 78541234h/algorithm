package chapter8;

import java.util.HashMap;

public class MaxSubArrLenAccToSpecValInUnsortedArray {
    public static void main(String[] args) {

    }

    public static void test() {

    }

    public static int maxSubArrayLen(int[] arr, int val) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); //第一次忘了加这个
        int sum = 0;
        int len = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(val - sum)) {
                len = Math.max(len, map.get(val - sum));
            }
            if (!map.containsKey(sum))
                map.put(sum, i);
        }
        return len;
    }

}
