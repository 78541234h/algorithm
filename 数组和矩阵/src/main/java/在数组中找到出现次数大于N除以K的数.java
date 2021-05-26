import java.util.*;

public class 在数组中找到出现次数大于N除以K的数 {
    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 4);
        map.put(3, 2);
        allCandsMinusOne(map);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    public static void printNumOccursOverHalf(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("no such element");
            return;
        }
        if (arr.length == 1 || (arr.length == 2 && arr[0] == arr[1])) {
            System.out.print(arr[0]);
            return;
        }
        int num = arr[0];
        int count = 1;
        for (int i = 1; i < arr.length; i++) {
            if (count == 0) {
                count = 1;
                num = arr[i];
            } else if (arr[i] == num) {
                count++;
            } else {
                count--;
            }
        }
        count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                count++;
            }
        }
        if (count > arr.length / 2) {
            System.out.println(num);
        } else {
            System.out.println("no such element");
        }
    }


    public static List<Integer> findKMajor(int[] arr, int k) {
        if (arr == null) {
            return Collections.emptyList();
        }
        ArrayList<Integer> res = new ArrayList<>();
        if (arr.length < k) {
            for (int i : arr) {
                res.add(i);
            }
            return res;
        }
        HashMap<Integer, Integer> cands = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (cands.containsKey(arr[i])) {
                cands.put(arr[i], cands.get(arr[i]) + 1);
            } else {
                if (cands.get(arr[i]) == k - 1) {
                    allCandsMinusOne(cands);
                } else {
                    cands.put(arr[i], 1);
                }
            }
        }
        HashMap<Integer, Integer> real = new HashMap<>();
        int threshold = arr.length / k;
        for (int i = 0; i < arr.length; i++) {
            int curNum = arr[i];
            if (cands.containsKey(curNum)) {
                Integer count = real.get(curNum);
                if (count == null) {
                    real.put(curNum, 1);
                } else {
                    if (count == threshold + 1) {
                        res.add(curNum);
                    }
                    real.put(curNum, count + 1);
                }
            }
        }
        return res;
    }

    public static void allCandsMinusOne(HashMap<Integer, Integer> cands) {
        List<Integer> removeList = new ArrayList<>(cands.size());
        for (Map.Entry<Integer, Integer> entry : cands.entrySet()) {
            if (entry.getValue() == 1) {
                removeList.add(entry.getKey());
            } else {
                entry.setValue(entry.getValue() - 1);
            }
        }
        for (Integer key : removeList) {
            cands.remove(key);
        }
    }
}
