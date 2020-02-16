package chapter8;

import utils.PrintUtil;
import utils.RandomUtil;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;

public class TopKInUnsortedArray {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Integer[] tArr = RandomUtil.randomIntArray(0, 100, 10000000);

        Integer[] res0 = new Integer[50];
        long start = System.currentTimeMillis();
        getTopKByHeap(tArr, 50, false, res0);
        long end = System.currentTimeMillis();
        PrintUtil.print(res0);
        PrintUtil.print(end - start);

        PrintUtil.newLine();

        Integer[] res1 = new Integer[50];
        start = System.currentTimeMillis();
        getTopKByBFPRT(tArr, 50, false, res1);
        end = System.currentTimeMillis();
        PrintUtil.print(res1);
        PrintUtil.print(end - start);
    }

    public static <T extends Comparable<T>> T[] getTopKByHeap(T[] arr, int K, boolean small, T[] res) {
        T[] heap = res;
        if (K >= arr.length) {
            System.arraycopy(arr, 0, heap, 0, arr.length);
        } else {
            for (int i = 0; i < arr.length; i++) {

                if (i < K) {
                    heap[i] = arr[i];
                    shiftUp(heap, i, !small);
                } else {
                    if (arr[i].compareTo(heap[0]) > 0 ^ small) {
                        heap[0] = arr[i];
                        shiftDown(heap, 0, heap.length, !small);
                    }
                }
            }
        }
        return heap;
    }

    private static <T extends Comparable<T>> void shiftDown(T[] heap, int index, int size, boolean small) {
        int left;
        T tmp = heap[index];
        while ((left = index * 2 + 1) < size) {
            if (left + 1 < size && (small ^ heap[left].compareTo(heap[left + 1]) < 0)) {
                left++;
            }
            if (tmp.compareTo(heap[left]) < 0 ^ small) {
                heap[index] = heap[left];
                index = left;
            } else break;
        }
        heap[index] = tmp;
    }

    private static <T extends Comparable<T>> void shiftUp(T[] heap, int index, boolean small) {
        T tmp = heap[index];
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap[parent].compareTo(tmp) < 0 ^ small) {
                heap[index] = heap[parent];
                index = parent;
            } else break;
        }
        heap[index] = tmp;
    }

    public static <T extends Comparable<T>> void getTopKByBFPRT(T[] arr, int K, boolean small, T[] res) {
        if (K > res.length) {
            System.arraycopy(arr, 0, res, 0, arr.length);
        }
        T Kth = small ? BFPRT(arr, K - 1) : BFPRT(arr, arr.length - K);
        int resIndex = 0;
        for (int i = 0; resIndex < res.length && i < arr.length; i++) {
            if (arr[i].compareTo(Kth) > 0 ^ small)
                res[resIndex++] = arr[i];
        }
        for (; resIndex < res.length; resIndex++)
            res[resIndex] = Kth;
    }


    private static <T extends Comparable<T>> T BFPRT(T[] arr, int K) {
        T[] tmp = (T[]) new Comparable[arr.length];
        System.arraycopy(arr, 0, tmp, 0, arr.length);
        return select(arr, 0, tmp.length - 1, K);
    }

    private static <T extends Comparable<T>> T select(T[] arr, int begin, int end, int K) {
        if (begin == end) return arr[begin];
        T median = medianOfMedians(arr, begin, end);
        int[] range = partition(arr, begin, end, median);
        if (K < range[0]) return select(arr, begin, range[0] - 1, K);
        else if (K > range[1]) return select(arr, range[1] + 1, end, K);
        else return arr[K];
    }

    private static <T extends Comparable<T>> T medianOfMedians(T[] arr, int begin, int end) {
        int num = end - begin + 1;
        T[] medians = (T[]) new Comparable[num / 5 + Math.min(1, num % 5)];
        for (int i = 0; i < medians.length; i++) {
            int beginI = begin + i * 5;
            int endI = Math.min(beginI + 4, end);
            medians[i] = getMedian(arr, beginI, endI);
        }
        return select(medians, 0, medians.length - 1, medians.length / 2);
    }

    private static <T extends Comparable<T>> T getMedian(T[] arr, int begin, int end) {
        insertSort(arr, begin, end);
        return arr[(begin + end + 1) / 2];
    }

    private static <T extends Comparable<T>> void insertSort(T[] arr, int begin, int end) {
        for (int i = begin + 1; i <= end; i++) {
            int j = i;
            T tmp = arr[i];
            while (--j >= begin && tmp.compareTo(arr[j]) < 0) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = tmp;
        }
    }

    private static <T extends Comparable<T>> int[] partition(T[] arr, int begin, int end, T pivotValue) {
        int small = begin - 1;
        int big = end + 1;
        int cur = begin;
        while (cur < big) {
            if (arr[cur].compareTo(pivotValue) < 0)
                swap(arr, ++small, cur++);
            else if (arr[cur].compareTo(pivotValue) > 0)
                swap(arr, --big, cur);
            else cur++;
        }
        int[] range = new int[2];
        range[0] = small + 1;
        range[1] = big - 1;
        return range;
    }

    private static <T> void swap(T[] arr, int a, int b) {
        T tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
