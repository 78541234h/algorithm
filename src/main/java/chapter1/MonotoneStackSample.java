package chapter1;

import java.util.LinkedList;
import java.util.Stack;

// 第一遍写bug 2个: 第一个method while 写成if 了. 第二个method没有判断stack是不是空

public class MonotoneStackSample {
    // not repeat array
    public static int[][] getLeftAndRightPositions(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        Integer popIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!stack.empty() && arr[stack.peek()] > arr[i]) {
                popIndex = stack.pop();
                res[popIndex][0] = stack.empty() ? -1 : stack.peek();
                res[popIndex][1] = i;
            }
            stack.push(i);
        }
        while (!stack.empty()) {
            popIndex = stack.pop();
            res[popIndex][0] = stack.empty() ? -1 : stack.peek();
            res[popIndex][1] = -1;
        }
        return res;
    }

    // repeated item
    public static int[][] getLeftAndRightPositions2(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<LinkedList<Integer>> stack = new Stack<>();
        LinkedList<Integer> indexes;
        for (int i = 0; i < arr.length; i++) {
            while (!stack.empty() && arr[stack.peek().peekLast()] > arr[i]) {
                indexes = stack.pop();
                int left = stack.empty() ? -1 : stack.peek().peekLast();
                for (Integer index : indexes) {
                    res[index][0] = left;
                    res[index][1] = i;
                }
            }
            if (stack.empty() || arr[i] != stack.peek().peekLast()) //要判空
                stack.push(new LinkedList<>());
            stack.peek().addLast(i);
        }
        while (!stack.empty()) {
            indexes = stack.pop();
            int left = stack.empty() ? -1 : stack.peek().peekLast();
            for (Integer index : indexes) {
                res[index][0] = left;
                res[index][1] = -1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {3, 4, 1, 5, 6, 2, 7};
        int[][] res = getLeftAndRightPositions(arr);
        for (int[] item : res) {
            System.out.println(item[0] + " " + item[1]);
        }

        System.out.println("====================");

        int[] arr2 = {3, 1, 3, 4, 3, 5, 3, 2, 2};
        int[][] res2 = getLeftAndRightPositions2(arr2);
        for (int[] item : res2) {
            System.out.println(item[0] + " " + item[1]);
        }
    }
}