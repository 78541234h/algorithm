package chapter1;

import java.util.Stack;

public class MaxSubMatrixSize {
    public static int getMaxSubMatrixSize(int[][] matrix) {
        int[] heights = new int[matrix[0].length];
        int res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                heights[j] = matrix[i][j] == 1 ? heights[j] + 1 : 0;
                while (!stack.empty() && heights[stack.peek()] > heights[j]) {
                    int pos = stack.pop();
                    int left = stack.empty() ? -1 : stack.peek();
                    res = Math.max(res, (j - left - 1) * heights[pos]);
                }
                if (stack.empty() || heights[stack.peek()] != heights[j])
                    stack.push(j);
            }
            while (!stack.empty()) {
                int pos = stack.pop();
                int left = stack.empty() ? -1 : stack.peek();
                res = Math.max(res, (heights.length - left - 1) * heights[pos]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 0, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 0}
        };
        System.out.println(getMaxSubMatrixSize(matrix));
    }
}

