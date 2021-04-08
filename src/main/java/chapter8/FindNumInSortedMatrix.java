package chapter8;

public class FindNumInSortedMatrix {

    public static void main(String[] args) {

    }

    public static boolean isContains(int[][] matrix, int num) {
        int col = matrix[0].length - 1;
        int row = 0;
        while (col >= 0 && row < matrix.length) {
            if (matrix[row][col] > num)
                row++;
            else if (matrix[row][col] < num)
                col--;
            else return true;
        }
        return false;
    }
}
