package chapter8;

public class MaxSubArrLenAccLessEqToSpecValInUnsortedArr {
    public static void main(String[] args) {

    }

    public static int maxLen(int[] arr, int val) {
        int[] minSums = new int[arr.length];
        int[] minSumEnds = new int[arr.length];
        int len = 0, end = 0, sum = 0;
        minSumEnds[arr.length - 1] = arr.length - 1;
        minSums[arr.length - 1] = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            if (minSums[i + 1] > 0) {
                minSumEnds[i] = i;
                minSums[i] = arr[i];
            } else {
                minSumEnds[i] = minSumEnds[i + 1];
                minSums[i] = arr[i] + minSums[i + 1];
            }
        }
        for (int i = 0; i < arr.length; i++) {
            while (end < arr.length && sum + minSums[end] <= val) {
                sum += minSums[end];
                end = minSumEnds[end] + 1;
            }
            len = Math.max(len, end - i);
            if (end > i) {
                sum -= arr[i];
            } else {
                end = i + 1;
            }
        }
        return len;
    }
}
