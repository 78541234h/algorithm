package chapter8;


//未排序正数数组中累加和为给定值的最长子数组长度
public class MaxSubArrLenAccToSpecValInUnsortedPlusArray {
    public static void main(String[] args) {

    }

    public static void test() {

    }

    public static int maxSubArrayLength(int[] arr, int val) {
        if (arr == null || arr.length < 1 || val < 1) return 0;
        int left = 0, right = 0;
        int sum = arr[0];
        int len = 0;
        while (true) {
            if (sum == val)
                len = Math.max(len, right - left + 1);
            else if (sum < val) {
                if (right == arr.length - 1)
                    break;
                sum += arr[++right];
            } else {
                sum -= arr[left++];
            }
        }
        return len;
    }
}
