package chapter5;

import utils.PrintUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ShortestDistanceOf2StrInArray {
    public static void main(String[] args) {
        //test();
        test2();
    }

    public static void test() {
        String[] arr = {"1", "3", "3", "3", "2", "3", "1"};
        PrintUtil.print(shortestDistance(arr, "1", "2"));
    }

    public static void test2() {
        String[] arr = {"1", "3", "3", "3", "2", "3", "1"};
        Record record = new Record(arr);
        PrintUtil.print(record.getShortestDistance("1", "2"));
        PrintUtil.print(record.getShortestDistance("1", "3"));
    }


    public static int shortestDistance(String[] arr, String s1, String s2) {
        if (s1 == null || s2 == null) return -1;
        if (s1.equals(s2)) return 0;
        int i1 = -1, i2 = -1;
        int dis = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(s1) || arr[i].equals(s2)) {
                i1 = arr[i].equals(s1) ? i : i1;
                i2 = arr[i].equals(s2) ? i : i2;
                if (i1 != -1 && i2 != -1)
                    dis = dis == -1 ? Math.abs(i1 - i2) : Math.min(dis, Math.abs(i1 - i2));
            }
        }
        return dis;
    }

    public static class Record {
        private HashMap<String, HashMap<String, Integer>> record;

        public Record(String[] arr) {
            this.record = new HashMap<>();
            HashMap<String, Integer> indexMap = new HashMap<>();
            for (int i = 0; i < arr.length; i++) {
                updateRecord(arr[i], i, indexMap);
                indexMap.put(arr[i], i);
            }
        }

        private void updateRecord(String str, int index, HashMap<String, Integer> indexMap) {
            if (!record.containsKey(str)) {
                record.put(str, new HashMap<>());
            }
            Set<Map.Entry<String, Integer>> entries = indexMap.entrySet();
            for (Map.Entry<String, Integer> entry : entries) {
                String key = entry.getKey();
                Integer entryVal = entry.getValue();
                if (!key.equals(str)) {
                    if (!record.get(key).containsKey(str)) {
                        record.get(key).put(str, index - entryVal);
                        record.get(str).put(key, index - entryVal);
                    } else {
                        if (index - entryVal < record.get(key).get(str)) {
                            record.get(key).put(str, index - entryVal);
                            record.get(str).put(key, index - entryVal);
                        }
                    }
                }
            }
        }

        public Integer getShortestDistance(String s1, String s2) {
            if (s1 == null || s2 == null) return -1;
            if (s1.equals(s2)) return 0;
            if (record.containsKey(s1) && record.containsKey(s2)) {
                return record.get(s1).get(s2);
            }
            return -1;
        }

    }
}
