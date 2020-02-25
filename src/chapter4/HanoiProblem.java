package chapter4;

import utils.PrintUtil;

public class HanoiProblem {
    public static void main(String[] args) {
        test2();
    }

    public static void test() {
        hanoi(3);
    }

    public static void test2() {
        int[] arr = {3, 3, 2, 1};
        PrintUtil.print(checkByLoop(arr));
    }


    public static void hanoi(int n) {
        func("left", "mid", "right", n);
    }

    public static void func(String s1, String s2, String s3, int n) {
        if (n > 1) {
            func(s1, s3, s2, n - 1);
            System.out.println("move " + n + ": " + s1 + " -> " + s3);
            func(s2, s1, s3, n - 1);
        } else {
            System.out.println("move 1: " + s1 + " -> " + s3);
        }
    }

    public static int checkByRecur(int[] arr) {
        return process(arr, arr.length - 1, 1, 2, 3);
    }

    public static int process(int[] arr, int i, int from, int mid, int to) {
        if (i == -1)
            return 0;
        if (arr[i] == mid)
            return -1;
        else if (arr[i] == from) {
            return process(arr, i - 1, from, to, mid);
        } else {
            int rest = process(arr, i - 1, mid, from, to);
            if (rest == -1)
                return -1;
            return 1 << i + rest;
        }
    }

    public static int checkByLoop(int[] arr) {
        int from = 1, mid = 2, to = 3;
        int index = arr.length - 1;
        int steps = 0;
        int tmp;
        while (index > 0) {
            if (arr[index] == mid) return -1;
            else if (arr[index] == from) {
                tmp = to;
                to = mid;
            } else {
                steps += 1 << index;
                tmp = from;
                from = mid;
            }
            mid = tmp;
            --index;
        }
        return steps;
    }
}
