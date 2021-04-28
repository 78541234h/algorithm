import java.util.Stack;

public class 最大子矩阵的大小 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 0, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 0}
        };
        System.out.println(maxArea(matrix));
    }


    public static int maxArea(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] heights = new int[cols];
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                heights[j] = matrix[i][j] == 0 ? 0 : heights[j] + matrix[i][j];
                while (!stack.empty() && heights[j] < heights[stack.peek()]) {
                    int height = heights[stack.pop()];
                    int left = stack.empty() ? -1 : stack.peek();
                    int width = j - left - 1;
                    maxArea = Math.max(maxArea, width * height);
                }
                stack.push(j);
            }
            while (!stack.empty()) {
                int height = heights[stack.pop()];
                int left = stack.empty() ? -1 : stack.peek();
                int width = cols - left - 1;
                maxArea = Math.max(maxArea, height * width);
            }
        }
        return maxArea;
    }
}
