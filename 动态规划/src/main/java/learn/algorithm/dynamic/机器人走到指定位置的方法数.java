package learn.algorithm.dynamic;

public class 机器人走到指定位置的方法数 {
    public static void main(String[] args) {
        System.out.print(pathNumDynamicCompress(5, 2, 3, 3));
    }

    public static int pathNumRecursive(int N, int M, int P, int K) {
        if (N < 3 || M < 0 || M > N || P < 0 || P > N || K < 0) {
            throw new RuntimeException("error");
        }
        if (K == 0)
            return P == M ? 1 : 0;
        if (P == 1)
            return pathNumRecursive(N, M, 2, K - 1);
        if (P == N)
            return pathNumRecursive(N, M, N - 1, K - 1);
        return pathNumRecursive(N, M, P - 1, K - 1) + pathNumRecursive(N, M, P + 1, K - 1);
    }


    public static int pathNumDynamic(int N, int M, int P, int K) {
        int[][] help = new int[K + 1][N + 1];
        help[0][M] = 1;
        for (int i = 1; i <= K; i++) {
            help[i][0] = help[i - 1][1];
            help[i][N] = help[i - 1][N - 1];
            //这里第一次写错了，写成了j = 2。。。
            for (int j = 1; j < N; j++) {
                help[i][j] = help[i - 1][j - 1] + help[i - 1][j + 1];
            }
        }
        return help[K][P];
    }

    public static int pathNumDynamicCompress(int N, int M, int P, int K) {
        int[] help = new int[N + 1];
        help[M] = 1;
        for (int i = 1; i <= K; i++) {
            int tmp1 = help[0];
            help[0] = help[1];
            help[N] = help[N - 1];
            for (int j = 1; j < N; j++) {
                int tmp2 = help[j];
                //这里第一次写成了help[j] = tmp1,光想着变量交换来着。。写代码一定要站在一个很高的角度来写。
                help[j] = tmp1 + help[j + 1];
                tmp1 = tmp2;
            }
        }
        return help[P];
    }


}
