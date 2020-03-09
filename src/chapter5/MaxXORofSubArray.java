package chapter5;

import utils.PrintUtil;

public class MaxXORofSubArray {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        int[] arr = {11, 1, 15, 10, 13, 4};
        PrintUtil.print(maxXORSubArray(arr));
    }

    private static class NumTrie {
        public Node root = new Node();

        public int maxXor(int val) {
            Node cur = root;
            int filter = 1 << 31;
            int res = 0;
            int best = 0;
            for (int i = 0; i < 32; i++) {
                filter = filter >> i;
                best = (filter & val) == 0 ? 1 : 0;
                if (i == 0) best = best ^ 1;
                best = cur.nexts[best] != null ? best : best ^ 1;
                cur = cur.nexts[best];
                res = best == 1 ? res | filter : res;
            }
            return res ^ val;
        }

        public void addNum(int val) {
            Node cur = root;
            int filter = 1 << 31;
            for (int j = 0; j < 32; j++) {
                filter = filter >> j;
                int path = (filter & val) == 0 ? 0 : 1;
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
            }
        }
    }

    public static int maxXORSubArray(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        if (arr.length == 1) return arr[0];
        NumTrie trie = new NumTrie();
        trie.addNum(0);
        int res = Integer.MIN_VALUE;
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            xor = xor ^ arr[i];
            res = Math.max(trie.maxXor(xor), res);
            trie.addNum(xor);
        }
        return res;
    }


    private static class Node {
        public Node[] nexts = new Node[2];
    }
}


