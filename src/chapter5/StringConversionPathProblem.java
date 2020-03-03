package chapter5;

import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;
import utils.PrintUtil;

import java.util.*;

public class StringConversionPathProblem {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        String[] arr = {"cab", "acc", "cbc", "ccc", "cac", "cbb", "aab", "abb"};
        LinkedList<ArrayList<String>> paths = findMinPaths("abc", "cab", arr);
        PrintUtil.print(paths);
    }

    private static HashMap<String, ArrayList<String>> getMap(List<String> words) {
        HashMap<String, ArrayList<String>> map = new HashMap<>(words.size());
        Set<String> dict = new HashSet<>(words);
        for (String word : words) {
            map.put(word, getNext(word, dict));
        }
        return map;
    }

    private static ArrayList<String> getNext(String word, Set<String> dict) {
        char[] chas = word.toCharArray();
        ArrayList<String> res = new ArrayList<>();
        for (int i = 0; i < chas.length; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != chas[i]) {
                    char tmp = chas[i];
                    chas[i] = c;
                    String change = String.valueOf(chas);
                    if (dict.contains(change))
                        res.add(change);
                    chas[i] = tmp;
                }
            }
        }
        return res;
    }

    private static HashMap<String, Integer> getDistances(HashMap<String, ArrayList<String>> map, String from) {
        HashMap<String, Integer> res = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(from);
        res.put(from, 0);
        while (!queue.isEmpty()) {
            String word = queue.poll();
            int dis = res.get(word);
            for (String next : map.get(word)) {
                if (!res.containsKey(next)) {
                    res.put(next, dis + 1);
                    queue.add(next);
                }
            }
        }
        return res;
    }

    private static void getShortestPaths(String from, String to, HashMap<String, ArrayList<String>> map,
                                         HashMap<String, Integer> dis, LinkedList<String> solution,
                                         LinkedList<ArrayList<String>> res) {
        solution.addLast(from);
        if (from.equals(to)) {
            res.add(new ArrayList<>(solution));
        } else {
            ArrayList<String> nexts = map.get(from);
            int distance = dis.get(from);
            for (String next : nexts) {
                if (dis.get(next) == distance + 1)
                    getShortestPaths(next, to, map, dis, solution, res);
            }
        }
        solution.pollLast();
    }

    public static LinkedList<ArrayList<String>> findMinPaths(String from, String to, String[] arr) {
        if (from == null || to == null) return null;
        LinkedList<ArrayList<String>> res = new LinkedList<>();
        if (from.equals(to)) {
            res.add(new ArrayList<>(Collections.singletonList(from)));
        } else {
            List<String> words = new LinkedList<>(Arrays.asList(arr));
            words.add(from);
            HashMap<String, ArrayList<String>> map = getMap(words);
            HashMap<String, Integer> distances = getDistances(map, from);
            LinkedList<String> solution = new LinkedList<>();
            getShortestPaths(from, to, map, distances, solution, res);
        }
        return res;
    }

}
