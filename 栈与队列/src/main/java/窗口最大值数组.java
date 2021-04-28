import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class 窗口最大值数组 {
    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        int[] res = maxNumsOfSlidWindows(arr, 10);
        System.out.println(Arrays.toString(res));
    }

    public static int[] maxNumsOfSlidWindows(int[] arr, int windowSize) {
        if (arr == null || arr.length == 0 || windowSize <= 0)
            return null;
        int[] res = new int[Math.max(1, arr.length - windowSize + 1)];
        Deque<Integer> deque = new LinkedList<>();
        deque.push(0);
        for (int i = 1; i < arr.length; i++) {
            if (deque.peekFirst() + windowSize == i) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[i]) {
                deque.pollLast();
            }
            deque.addLast(i);
            if (i + 1 >= windowSize) {
                res[i + 1 - windowSize] = arr[deque.peekFirst()];
            }
        }
        if (windowSize > arr.length) {
            res[0] = arr[deque.peekFirst()];
        }
        return res;
    }
}
