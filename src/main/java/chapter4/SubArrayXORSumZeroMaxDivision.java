package chapter4;

import java.util.HashMap;
import java.util.Map;

public class SubArrayXORSumZeroMaxDivision {
    public static void main(String[] args) {

    }

    public static void test() {

    }


    public static int maxDivision(int[] arr) {
        if (arr == null || arr.length == 0) return 1;
        Map<Integer, Integer> map = new HashMap<>();
        int[] dp = new int[arr.length];
        map.put(0, -1);
        map.put(arr[0], 0);
        int xor = arr[0];
        dp[0] = xor == 0 ? 1 : 0;
        for (int i = 1; i < arr.length; i++) {
            xor ^= arr[i];
            if (map.containsKey(xor)) {
                int preXORIndex = map.get(xor);
                dp[i] = preXORIndex == -1 ? 0 : (dp[preXORIndex] + 1); //第一次写，这里忘了加括号
            }
            dp[i] = Math.max(dp[i], dp[i - 1]);
            map.put(i, xor);
        }
        return dp[arr.length - 1];
    }
}
