package chapter8;

import utils.PrintUtil;
import utils.RandomUtil;

public class OddPosOddValOrEvenPosEvenVal {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        Integer[] arr = RandomUtil.randomIntArray(0, 9999, 10000);
        adjust(arr);
        boolean odd = true;
        boolean even = true;
        for (int i = 0; i < arr.length; i++) {
            if ((i & 1) == 1 && (arr[i] & 1) != 1)
                odd = false;
            if ((i & 1) == 0 && (arr[i] & 1) != 0)
                even = false;
            if (!(odd || even))
                break;
        }
        PrintUtil.print(odd || even);
    }

    public static void adjust(Integer[] arr) {
        int oddIndex = 1;
        int evenIndex = 0;
        while (oddIndex < arr.length && evenIndex < arr.length) {
            if ((arr[evenIndex] & 1) == 0)
                evenIndex += 2;
            else if ((arr[oddIndex] & 1) != 0)
                oddIndex += 2;
            else {
                swap(arr, oddIndex, evenIndex);
                evenIndex += 2;
                oddIndex += 2;
            }
        }
    }

    public static void swap(Integer[] arr, int i1, int i2) {
        int tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
    }


}
