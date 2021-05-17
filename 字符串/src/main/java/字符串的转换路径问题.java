import java.util.*;

public class 字符串的转换路径问题 {
    public static void main(String[] args) {
        String[] strs = {"cac", "cab", "aab", "abb", "acc", "cbc", "ccc", "cbb"};
        List<List<String>> paths = getShortestPaths("abc", "cab", strs);
        for (List<String> path : paths) {
            path.forEach((s) -> System.out.print(s + " "));
            System.out.println();
        }

//        List<List<String>> paths = findMinPaths("abc", "cab", new ArrayList<>(Arrays.asList(strs)));
//        for (List<String> path : paths) {
//            path.forEach((s) -> System.out.print(s + " "));
//            System.out.println();
//        }
    }

    public static List<List<String>> getShortestPaths(String start, String end, String[] list) {
        ArrayList<String> arr = new ArrayList<>(Arrays.asList(list));
        arr.add(start);
        HashMap<String, HashSet<String>> map = buildMap(arr);
        HashMap<String, Integer> distances = getDistances(map, start);
        List<List<String>> paths = new LinkedList<>();
        getPaths(start, end, map, distances, new LinkedList<>(), paths);
        return paths;
    }

    public static HashMap<String, Integer> getDistances(HashMap<String, HashSet<String>> map, String start) {
        HashMap<String, Integer> distances = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        distances.put(start, 0);
        while (!queue.isEmpty()) {
            String cur = queue.remove();
            int dis = distances.get(cur);
            for (String next : map.get(cur)) {
                if (!distances.containsKey(next)) {
                    distances.put(next, dis + 1);
                    queue.add(next);
                }
            }
        }
        return distances;
    }

    public static void getPaths(String cur, String end,
                                HashMap<String, HashSet<String>> map,
                                HashMap<String, Integer> distances,
                                LinkedList<String> path, List<List<String>> paths) {
        path.addLast(cur);
        if (cur.equals(end)) {
            paths.add(new ArrayList<>(path));
        } else {
            int dis = distances.get(cur);
            for (String s : map.get(cur)) {
                //第一次写的时候忘了加下面的判断条件，导致死循环
                if (distances.get(s) == dis + 1) {
                    getPaths(s, end, map, distances, path, paths);
                }
            }
        }
        path.removeLast();
    }


    private static HashMap<String, HashSet<String>> buildMap(ArrayList<String> list) {
        HashMap<String, HashSet<String>> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            if (!map.containsKey(list.get(i))) {
                map.put(list.get(i), new HashSet<>());
                for (int j = 0; j < i; j++) {
                    if (list.get(i).length() == list.get(j).length() &&
                            getDistance(list.get(i), list.get(j)) == 1) {
                        map.get(list.get(i)).add(list.get(j));
                        map.get(list.get(j)).add(list.get(i));
                    }
                }
            }
        }
        return map;
    }

    private static int getDistance(String s1, String s2) {
        int len = s1.length();
        int dis = 0;
        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) != s2.charAt(i))
                dis++;
        }
        return dis;
    }

}
