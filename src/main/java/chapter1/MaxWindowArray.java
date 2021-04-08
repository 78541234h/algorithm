package chapter1;

import java.util.Deque;
import java.util.LinkedList;

public class MaxWindowArray {
    public static int[] getMaxValOfWindows(int[] arr, int w) {
        if (w < 1 || arr == null || arr.length < 1) return null;
        int[] res = arr.length > w ? new int[arr.length - w + 1] : new int[1];
        Deque<Integer> win = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            while (!win.isEmpty() && arr[win.peekLast()] < arr[i]) {
                win.pollLast();
            }
            win.addLast(i);
            if (win.peekFirst() == (i - w)) win.pollFirst();
            if (i >= w - 1) res[i - w + 1] = arr[win.peekFirst()];
        }
        if (res.length == 1) res[0] = win.pollFirst();
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        int[] res = getMaxValOfWindows(arr, 7);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}
