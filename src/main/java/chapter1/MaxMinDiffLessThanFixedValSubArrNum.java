package chapter1;

import utils.PrintUtil;
import utils.RandomUtil;

import java.util.LinkedList;


public class MaxMinDiffLessThanFixedValSubArrNum {
    public static void main(String[] args) {
        test();
    }

    public static boolean test() {
        Integer[] arr = RandomUtil.randomIntArray(0, 100, 100);
        PrintUtil.print(getSubArrayNum1(arr, 10));
        PrintUtil.newLine();
        PrintUtil.print(getSubArrayNum2(arr, 10));
        return false;
    }

    public static int getSubArrayNum1(Integer[] arr, int diff) {
        LinkedList<Integer> qmin = new LinkedList<>();
        LinkedList<Integer> qmax = new LinkedList<>();
        int head = 0, tail = 0;
        int num = 0;
        while (head < arr.length) {
            while (tail < arr.length) {
                if (qmin.isEmpty() || qmin.peekLast() != tail) {
                    while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[tail])
                        qmax.pollLast();
                    qmax.offerLast(tail);
                    while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[tail])
                        qmin.pollLast();
                    qmin.offerLast(tail);
                }
                if (arr[qmax.peek()] - arr[qmin.peek()] > diff)
                    break;
                tail++;
            }
            num += tail - head;
            if (qmin.peekFirst() == head)
                qmin.pollFirst();
            if (qmax.peekFirst() == head)
                qmax.pollFirst();
            head++;
        }
        return num;
    }

    public static int getSubArrayNum2(Integer[] arr, int diff) {
        LinkedList<Integer> qmin = new LinkedList<>();
        LinkedList<Integer> qmax = new LinkedList<>();
        int head = 0, tail = 0, num = 0;
        while (tail < arr.length) {
            while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[tail])
                qmin.pollLast();
            qmin.offerLast(tail);
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[tail])
                qmax.pollLast();
            qmax.offerLast(tail);
            while (head < arr.length) {
                if (!qmin.isEmpty() && arr[qmax.peek()] - arr[qmin.peek()] > diff) {
                    if (qmin.peek() == head)
                        qmin.poll();
                    if (qmax.peek() == head)
                        qmax.poll();
                    head++;
                } else {
                    break;
                }
            }
            num += tail - head + 1;
            tail++;
        }
        return num;
    }
}
