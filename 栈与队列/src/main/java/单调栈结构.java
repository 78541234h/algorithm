import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class 单调栈结构 {
    public static void main(String[] args) {
        int[] arr = {3, 4, 1, 5, 6, 2, 7};
        int[][] res = getNearLessNoRepeat(arr);
//        for (int[] item : res) {
//            System.out.println(Arrays.toString(item));
//        }

        int[] arr1 = {3, 1, 3, 4, 3, 5, 3, 2, 2};
        res = getNearLessRepeat(arr1);
        for (int[] item : res) {
            System.out.println(Arrays.toString(item));
        }
    }

    public static int[][] getNearLessNoRepeat(int[] arr) {
        if (arr == null || arr.length == 0)
            return null;
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.empty() && arr[i] < arr[stack.peek()]) {
                int pop = stack.pop();
                res[pop][0] = stack.empty() ? -1 : stack.peek();
                res[pop][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int pop = stack.pop();
            res[pop][0] = stack.isEmpty() ? -1 : stack.peek();
            res[pop][1] = -1;
        }
        return res;
    }


    public static int[][] getNearLessRepeat(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        Stack<LinkedList<Integer>> stack = new Stack<>();
        int[][] res = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            while (!stack.empty() && arr[i] < arr[stack.peek().peek()]) {
                LinkedList<Integer> list = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().peek();
                for (Integer idx : list) {
                    res[idx][0] = leftLessIndex;
                    res[idx][1] = i;
                }
            }
            if (stack.isEmpty() || arr[stack.peek().peek()] < arr[i]) {
                LinkedList<Integer> list = new LinkedList<>();
                list.push(i);
                stack.push(list);
            } else {
                stack.peek().push(i);
            }
        }
        while (!stack.empty()) {
            LinkedList<Integer> list = stack.pop();
            int leftLessIndex = stack.empty() ? -1 : stack.peek().peek();
            for (Integer idx : list) {
                res[idx][0] = leftLessIndex;
                res[idx][1] = -1;
            }
        }
        return res;
    }
}
