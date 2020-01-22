package chapter4;

public class MinCurrencyForMoney {
    public static void main(String[] args) {
        test3();
    }

    private static void test() {
        int[] currency = {3, 2, 5};
        int min = getMinCurrency(currency, 20);
        System.out.println(min);
    }

    private static void test2() {
        int[] currency = {3, 5};
        int min = getMinCurrency(currency, 2);
        System.out.println(min);
    }

    private static void test3() {
        int[] currency = {3, 2, 5};
        int min = getMinCurrency4(currency, 20);
        System.out.println(min);
    }

    public static int getMinCurrency(int[] currency, int money) {
        return getMinCurrency0(currency, 0, money);
    }

    private static int getMinCurrency0(int[] currency, int index, int money) {
        if (money == 0) return 0;
        if (index == currency.length) return -1;
        int res = Integer.MAX_VALUE;
        for (int i = money / (currency[index]); i >= 0; i--) {
            int tmp = getMinCurrency0(currency, index + 1, money - i * currency[index]);
            if (tmp != -1)
                res = Math.min(res, tmp + i);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public static int getMinCurrency2(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 0) return -1;
        int[][] dp = new int[arr.length + 1][aim + 1];
        for (int i = 1; i <= aim; i++) {
            dp[arr.length][i] = -1;
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[i][rest] = dp[i + 1][rest];
                if (rest - arr[i] >= 0 && dp[i][rest - arr[i]] != -1) {
                    if (dp[i][rest] == -1) {
                        dp[i][rest] = dp[i][rest - arr[i]] + 1;
                    } else {
                        dp[i][rest] = Math.min(dp[i][rest], dp[i][rest - arr[i]] + 1);
                    }
                }
            }
        }
        return dp[0][aim];
    }

    public static int getMinCurrency3(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 0) return -1;
        int[][] dp = new int[arr.length][aim + 1];
        for (int i = 0; i <= aim; i++) {
            dp[0][i] = i % arr[0] == 0 ? i / arr[0] : -1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[i][rest] = dp[i - 1][rest];
                if (rest - arr[i] >= 0 && dp[i][rest - arr[i]] != -1) {
                    if (dp[i][rest] == -1) {
                        dp[i][rest] = dp[i][rest - arr[i]] + 1;
                    } else {
                        dp[i][rest] = Math.min(dp[i][rest - arr[i]] + 1, dp[i][rest]);
                    }
                }
            }
        }
        return dp[arr.length - 1][aim];
    }


    // 空间复杂度为　Ｏ(aim), 因为　dp[i][rest] 依赖于　dp[i][rest - arr[i]]　而不是　dp[i][rest -1] 所以只能对一个维度进行压缩
    public static int getMinCurrency4(int[] arr, int aim) {
        if (arr == null || arr.length < 1 || aim < 0) return -1;
        if (aim == 0) return 0;
        int[] dp = new int[aim + 1];
        for (int i = 0; i <= aim; i++) {
            dp[i] = i % arr[0] == 0 ? i / arr[0] : -1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int rest = 0; rest <= aim; rest++) {
                if (rest - arr[i] >= 0 && dp[rest - arr[i]] != -1) {
                    dp[rest] = dp[rest] == -1 ?
                            dp[rest - arr[i]] + 1 :
                            Math.min(dp[rest], dp[rest - arr[i]] + 1);
                }
            }
        }
        return dp[aim];
    }
}
