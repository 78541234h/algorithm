package chapter8;

import utils.PrintUtil;

import java.util.Vector;

public class PrintTuplesAddUpToGivenValueInSortedArray {
    public static void main(String[] args) {

    }

    public static void printUniquePair(int[] arr, int K) {
        if (arr == null || arr.length < 2) return;
        int left = 0;
        int right = arr.length - 1;
        while (left != right) {
            int sum = arr[left] + arr[right];
            if (sum == K) {
                if (left == 0 || arr[left] != arr[left - 1])
                    System.out.println("[" + arr[left] + "," + arr[right] + "]");
                left++; //第一遍这里没有加
                right--;
            } else if (sum > K)
                right--;
            else
                left++;
        }
    }

    public static void printUniqueTraid(int[] arr, int K) {
        if (arr == null || arr.length < 3) return;
        Vector<Vector<Integer>> res = new Vector<>();
        for (int i = 0; i < arr.length - 2; i++) {
            if (i == 0 || arr[i] != arr[i - 1]) {
                int left = i + 1;
                int right = arr.length - 1;
                int rest = K - arr[i];
                while (left < right) {
                    int sum = arr[left] + arr[right];
                    if (sum > rest)
                        right--;
                    else if (sum < rest)
                        left++;
                    else {
                        if (left == i + 1 || arr[left] != arr[left - 1])
                            System.out.println("[" + arr[i] + "," + arr[left] + "," + arr[right] + "]");
                        left++;
                        right--;
                    }
                }
            }
        }
    }


}
