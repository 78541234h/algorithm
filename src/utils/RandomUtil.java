package utils;

import java.util.Random;

public class RandomUtil {
    public static Integer[] randomIntArray(int min, int max, int length) {
        Integer[] arr = new Integer[length];
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            arr[i] = rand.nextInt(max - min + 1) + min;
        }
        return arr;
    }
}
