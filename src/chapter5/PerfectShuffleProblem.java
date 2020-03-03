package chapter5;

import utils.PrintUtil;

public class PerfectShuffleProblem {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        shuffle(arr);
        PrintUtil.print(arr);
    }

    public static int modifyIndex(int i, int len) {
        return (2 * i) % (len + 1);
    }

    public static void shuffle(int[] arr) {
        shuffle(arr, 0, arr.length - 1);
    }

    public static void shuffle(int[] arr, int start, int end) {
        int size;
        while ((size = end - start + 1) > 1) {
            int tmp = size + 1;
            int k = 1;
            while ((tmp /= 3) > 0) k *= 3;
            int rs = start + (k - 1) / 2;
            int s = (size - k + 1) / 2;
            int re = rs + size / 2 - 1;
            rotate(arr, rs, re, s);
            cycle(arr, start, start + k - 2);
            start = start + k - 1;
        }
    }

    public static void cycle(int[] arr, int start, int end) {
        int size = end - start + 1;
        for (int k = size + 1, cycleStart = 1; k > 1; k /= 3) {
            int cur = cycleStart;
            int curVal = arr[cur + start - 1];
            do {
                int next = modifyIndex(cur, size);
                int nextVal = arr[next + start - 1];
                arr[next + start - 1] = curVal;
                curVal = nextVal;
                cur = next;
            } while (cur != cycleStart);
            cycleStart *= 3;
        }
    }

    public static void rotate(int[] arr, int start, int end, int size) {
        reverse(arr, start, start + size - 1);
        reverse(arr, start + size, end);
        reverse(arr, start, end);
    }

    public static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int tmp = arr[start];
            arr[start++] = arr[end];
            arr[end--] = tmp;
        }
    }
}