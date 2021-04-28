package learn.algorithm.dynamic;

public class 换钱的方法数 {
    public static void main(String[] args) {
        int[] arr = {5, 10, 25, 1};
        int aim = 15;
        System.out.print(exchangeMoneyNum(arr, aim));
    }

    public static int exchangeMoneyNum(int[] arr, int aim) {
        int[] dp = new int[aim + 1];
        for (int k = 0; k * arr[arr.length - 1] <= aim; k++) {
            dp[k * arr[arr.length - 1]] = 1;
        }
        for (int i = arr.length - 2; i >= 0; i--) {
            for (int j = 1; j <= aim; j++) {
                if (j >= arr[i])
                    dp[j] += dp[j - arr[i]];
            }
        }
        return dp[aim];
    }
}
