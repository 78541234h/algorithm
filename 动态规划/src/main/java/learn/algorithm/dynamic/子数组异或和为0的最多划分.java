package learn.algorithm.dynamic;

import java.util.HashMap;

public class 子数组异或和为0的最多划分 {
    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 9, 0, 7, 0, 2, 1, 3};
        System.out.print(maxZeroXorNum(arr));
    }

    public static int maxZeroXorNum(int[] arr) {
        HashMap<Integer, Integer> xorToIdxMap = new HashMap<>();
        int[] help = new int[arr.length];
        int xor = arr[0];
        help[0] = xor == 0 ? 1 : 0;
        //第一次写没有考虑到这种情况
        xorToIdxMap.put(0, -1);
        xorToIdxMap.put(xor, 0);
        for (int i = 1; i < arr.length; i++) {
            xor = xor ^ arr[i];
            //第一次写这里弄成了get(arr[i])
            Integer prev = xorToIdxMap.get(xor);
            if (prev != null) {
                help[i] = prev == -1 ? 1 : help[prev] + 1;
            }
            help[i] = Math.max(help[i], help[i - 1]);
            xorToIdxMap.put(xor, i);
        }
        return help[arr.length - 1];
    }
}
