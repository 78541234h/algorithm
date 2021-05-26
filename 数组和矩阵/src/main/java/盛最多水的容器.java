public class 盛最多水的容器 {
    public static void main(String[] args) {

    }

    public static int maxVolume(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int L = 0;
        int R = arr.length - 1;
        int res = 0;
        while (L < R) {
            int minHeight = Math.min(arr[L], arr[R]);
            res = Math.max(res, minHeight * (R - L));
            while (L < R && arr[L] <= minHeight) L++;
            while (L < R && arr[R] <= minHeight) R--;
        }
        return res;
    }
}
