package learn.algorithm.dynamic;

public class N皇后问题 {
    public static void main(String[] args) {
        //for (int i = 1; i <= 8; i++) {
            System.out.println(nQueens(16));
        //}
    }

    public static int nQueens(int N) {
        //board{i} = j，表示第i行的皇后棋子位于第j列上。
        int[] board = new int[N];
        return process(board, 0, N);
    }

    private static int process(int[] board, int row, int N) {
        if (row == N)
            return 1;
        int res = 0;
        for (int col = 0; col < N; col++) {
            if (check(board, row, col)) {
                board[row] = col;
                res += process(board, row + 1, N);
            }
        }
        return res;
    }

    private static boolean check(int[] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (row - i == Math.abs(col - board[i]) || col == board[i])
                return false;
        }
        return true;
    }


}
