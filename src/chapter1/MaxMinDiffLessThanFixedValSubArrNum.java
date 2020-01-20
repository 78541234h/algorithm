package chapter1;

import java.util.LinkedList;

public class MaxMinDiffLessThanFixedValSubArrNum {
    public static void main(String[] args) {

    }

    private static int getSubArrayNum(Integer[] arr, int diff) {
        if (arr == null || arr.length < 1 || diff < 1) return 0;
        LinkedList<Integer> qmax = new LinkedList<>();
        LinkedList<Integer> qmin = new LinkedList<>();
        int head = 0, tail = 0, res = 0;
        while (head < arr.length) {
            while (tail < arr.length) {
                if (qmin.isEmpty() || qmin.peekLast() != tail) {
                    while (!qmax.isEmpty() && arr[qmax.peekLast()] < arr[tail]) {
                        qmax.pollLast();
                    }
                    qmax.addLast(tail);
                    while (!qmin.isEmpty() && arr[qmin.peekFirst()] > arr[tail]) {
                        qmin.pollLast();
                    }
                    qmin.addLast(tail);
                }
                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > diff)
                    break;
                tail++;
            }
            res += tail - head;
            head++;
            if (qmax.getFirst() == head)
                qmax.pollFirst();
            if (qmin.getFirst() == head)
                qmin.pollFirst();
        }
        return res;
    }
}
