package chapter8;


// 返回最小需要排序的子数组的长度, 使得整体数组有序
public class MinNeedSortedSubArrayLength {
    public static void main(String[] args) {

    }

    public static int getNeedSortedSubArrayLength(Integer[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int left = -1, right = -1;
        int min = arr[arr.length - 1];
        int max = arr[0];
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > min) {
                left = i;
            } else {
                min = arr[i];
            }
        }
        if (left == -1)
            return 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < max) {
                right = i;
            } else max = arr[i];
        }
        return right - left + 1;
    }

}
