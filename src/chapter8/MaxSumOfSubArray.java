package chapter8;

public class MaxSumOfSubArray {
    public static void main(String[] args) {

    }

    public static int maxSumOfSubArr(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            cur = Math.max(0, cur);
        }
        return max;
    }
}
