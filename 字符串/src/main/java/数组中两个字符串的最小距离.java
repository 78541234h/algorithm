import sun.nio.cs.ext.MacHebrew;

import java.util.HashMap;
import java.util.Map;

public class 数组中两个字符串的最小距离 {
    public static void main(String[] args) {
        String[] strs = {"1", "2", "3", "3", "5"};
//        System.out.println(minDis(strs, "3", "3"));

        DistanceRecord record = new DistanceRecord(strs);
        System.out.println(record.minDistance("2", "3"));
    }


    public static int minDis(String[] strs, String s1, String s2) {
        if (strs == null || strs.length == 0) return -1;

        int minDis = Integer.MAX_VALUE;
        int lastS1Pos = -1;
        int lastS2Pos = -1;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].equals(s1)) {
                lastS1Pos = i;
                if (lastS2Pos != -1) {
                    minDis = Math.min(minDis, lastS1Pos - lastS2Pos);
                }
            }
            if (strs[i].equals(s2)) {
                lastS2Pos = i;
                if (lastS1Pos != -1) {
                    minDis = Math.min(minDis, lastS2Pos - lastS1Pos);
                }
            }
        }

        return minDis == Integer.MAX_VALUE ? -1 : minDis;
    }


    public static class DistanceRecord {
        HashMap<String, HashMap<String, Integer>> records = new HashMap<>();

        public DistanceRecord(String[] strs) {
            HashMap<String, Integer> lastPosMap = new HashMap<>();
            for (int i = 0; i < strs.length; i++) {
                String cur = strs[i];
                lastPosMap.put(cur, i);
                records.putIfAbsent(cur, new HashMap<>());
                HashMap<String, Integer> map = records.get(cur);
                map.put(cur, 0);
                for (Map.Entry<String, HashMap<String, Integer>> entry : records.entrySet()) {
                    int dis = i - lastPosMap.get(entry.getKey());
                    int minDis;
                    entry.getValue().putIfAbsent(cur, dis);
                    if ((minDis = entry.getValue().get(cur)) > dis) {
                        minDis = dis;
                        entry.getValue().put(cur, minDis);
                    }
                    map.put(entry.getKey(), minDis);
                }
            }
        }

        public int minDistance(String s1, String s2) {
            HashMap<String, Integer> map = records.get(s1);
            if (map != null) {
                Integer res = map.get(s2);
                return res == null ? -1 : res;
            }
            return -1;
        }
    }
}
