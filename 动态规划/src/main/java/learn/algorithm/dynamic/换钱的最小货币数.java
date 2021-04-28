package learn.algorithm.dynamic;

public class 换钱的最小货币数 {
    public static void main(String[] args) {
        int[] arr = {2, 3, 5};
        System.out.print(minCoinsDynamic(arr, 20));

    }

    public static int minCoins(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    private static int process(int[] arr, int i, int rest) {
        if (i == arr.length - 1) {
            if (rest % arr[i] != 0)
                return -1;
            return rest / arr[i];
        }
        int res = -1;
        for (int k = 0; k <= rest / arr[i]; k++) {
            int next = process(arr, i + 1, rest - arr[i] * k);
            if (next != -1) {
                res = res == -1 ? next + k : Math.min(res, next + k);
            }
        }
        return res;
    }


    public static int minCoinsDynamic(int[] arr, int aim) {
        int[][] help = new int[arr.length][aim + 1];
        for (int i = 1; i <= aim; i++) {
            if (i % arr[arr.length - 1] == 0)
                help[arr.length - 1][i] = i / arr[arr.length - 1];
            else
                help[arr.length - 1][i] = -1;
        }

        for (int i = arr.length - 2; i >= 0; i--) {
            for (int j = 1; j <= aim; j++) {
                help[i][j] = -1;
                int left = j - arr[i] >= 0 ? help[i][j - arr[i]] : -1;
                int below = help[i + 1][j];
                if (left != -1)
                    help[i][j] = left + 1;
                if (below != -1)
                    //下面第一次写错了，写成了help[i][j] = Math.min(help[i][j],below)，没有看到在这里help[i][j]可能为-1;
                    help[i][j] = help[i][j] == -1 ? below : Math.min(help[i][j], below);
            }
        }
        return help[0][aim];
    }

    public static int minCoinsDynamicCompress(int[] arr, int aim) {
        int[] help = new int[aim + 1];
        int lastCoin = arr[arr.length - 1];
        for (int i = 1; i <= aim; i++) {
            if (i % lastCoin == 0)
                help[i] = i / lastCoin;
            else
                help[i] = -1;
        }

        for (int i = arr.length - 2; i >= 0; i--) {
            for (int j = 1; j <= aim; j++) {
                int left = j - arr[i] >= 0 ? help[j - arr[i]] : -1;
                if (left != -1) {
                    //这里第一次写错了，left + 1写成了left，主要还是对变量的定义搞混了，left到底表示help[j - arr[i]]
                    //还是help[j - arr[i]] + 1，前后要一致。
                    help[j] = help[j] == -1 ? left + 1 : Math.min(help[j], left + 1);
                }
            }
        }
        return help[aim];
    }
}
