package chapter8;

import utils.PrintUtil;

import java.util.*;

//在数组中找到出现次数大于一半的数
//在数组中找到出现次数大于N/K的数
public class FindNumInArrayByOccurrence {
    public static void main(String[] args) {
        //test();
        HashMap<Integer, Integer> map = new HashMap<>(1);
        map.put(1, 1);
        map.put(2, 2);

        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            iterator.remove();
        }
        System.out.println(map.get(1));
    }

    public static void test() {
        Integer[] arr = {1, 2, 3, 3, 3, 3};
        Integer i = findNumOfOccMoreThanHalfArrayLength(arr);
        PrintUtil.print(i);
    }

    public static Integer findNumOfOccMoreThanHalfArrayLength(Integer[] arr) {
        int times = 0, cand = 0;
        for (int i = 0; i < arr.length; i++) {
            if (times == 0) {
                cand = arr[i];
                times = 1;
            } else if (arr[i] == cand) {
                times++;
            } else {
                times--;
            }
        }
        times = 0;
        for (Integer integer : arr) {
            if (integer == cand)
                times++;
        }
        if (times > arr.length / 2)
            return cand;
        else
            return null;
    }


    public static Collection<Integer> findNumsByOccurrence(Integer[] arr, Integer K) {
        HashMap<Integer, Integer> cands = new HashMap<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            if (cands.containsKey(arr[i])) {
                cands.put(arr[i], cands.get(arr[i]) + 1);
            } else if (cands.size() < K - 1) {
                cands.put(arr[i], 1);
            } else {
                Iterator<Map.Entry<Integer, Integer>> iterator = cands.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<Integer, Integer> entry = iterator.next();
                    int occurrences = entry.getValue();
                    if (occurrences == 1)
                        iterator.remove();
                    else entry.setValue(occurrences - 1);
                }
            }
        }
        Iterator<Map.Entry<Integer, Integer>> iterator = cands.entrySet().iterator();
        while (iterator.hasNext()) {
            iterator.next().setValue(0);
        }
        for (int i = 0; i < arr.length; i++) {
            if (cands.get(arr[i]) != null) {
                cands.put(arr[i], cands.get(arr[i]));
            }
        }
        Vector<Integer> res = new Vector<>();
        iterator = cands.entrySet().iterator();
        Integer num = arr.length / K;
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            if (entry.getValue() > num)
                res.add(entry.getKey());
        }
        return res;
    }

}
