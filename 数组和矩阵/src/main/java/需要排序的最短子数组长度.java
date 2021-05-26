public class 需要排序的最短子数组长度 {
    public static void main(String[] args) {

    }

    public static int getMinLength(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int left = -1;
        int right = -1;
        int min = arr[arr.length - 1];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < max) {
                right = i;
            } else {
                max = arr[i];
            }
        }
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > min) {
                left = i;
            } else {
                min = arr[i];
            }
        }
        return right - left + 1;
    }
}
