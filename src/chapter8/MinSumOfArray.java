package chapter8;

import utils.PrintUtil;
import utils.RandomUtil;

//计算数组的小和，小和的定义为，小和的定义为对于数组中每个数左边小于等于它的数的和的累计。
public class MinSumOfArray {
    public static void main(String[] args) {
        test();
    }

    public static void print(Integer[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.print("\n\r");
    }

    public static void testMergeSort() {
        Integer[] arr = RandomUtil.randomIntArray(0, 1000, 1000);
        merge_sort(arr, 0, 999);
        print(arr);
    }

    public static void test() {
        int[] arr = {1, 3, 5, 2, 4, 6};
        PrintUtil.print(calculate(arr));

    }

    public static int calculate(int[] arr) {
        int[] tmp = new int[arr.length];
        System.arraycopy(arr, 0, tmp, 0, arr.length);
        return process(tmp, 0, arr.length - 1);
    }

    private static int process(int[] arr, int begin, int end) {
        int sum = 0;
        if (begin < end) {
            int mid = (begin + end) / 2;
            int lsum = process(arr, begin, mid);
            int rsum = process(arr, mid + 1, end);
            sum = lsum + rsum;
            int[] copy = new int[end - begin + 1];
            for (int i = 0, j = begin; i < copy.length; i++, j++) {
                copy[i] = arr[j];
            }
            int lp = begin, rp = mid + 1;
            int cur = 0;
            while (lp <= mid && rp <= end) {
                int cand;
                if (arr[lp] <= arr[rp]) {
                    cand = arr[lp++];
                    sum += cand * (end - rp + 1);
                } else {
                    cand = arr[rp++];
                }
                copy[cur++] = cand;
            }
            if (lp <= mid) {
                while (lp <= mid)
                    copy[cur++] = arr[lp++];
            } else {
                while (rp <= end)
                    copy[cur++] = arr[rp++];
            }

            for (int i = 0, j = begin; i < copy.length; i++, j++)
                arr[j] = copy[i];
        }
        return sum;
    }

    public static void merge_sort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int mid = (begin + end) / 2;
            merge_sort(arr, begin, mid);
            merge_sort(arr, mid + 1, end);
            int[] lpart = new int[mid - begin + 1];
            for (int i = begin, j = 0; i <= mid; i++, j++) // 第一次写， 这里 忘了 j++
                lpart[j] = arr[i];
            int lp = 0;
            int lpartLen = mid - begin + 1;
            int rp = mid + 1;
            int cur = begin;
            while (lp < lpartLen && rp <= end) { //第一次写这里lp 的条件写成 lp < mid 了
                int cand;
                if (lpart[lp] <= arr[rp]) {
                    cand = lpart[lp++];
                } else {
                    cand = arr[rp++];
                }
                arr[cur++] = cand;
            }
            if (lp < lpartLen) {
                while (lp < lpartLen)
                    arr[cur++] = lpart[lp++];
            } else
                while (rp <= end)
                    arr[cur++] = arr[rp++];
        }
    }


}