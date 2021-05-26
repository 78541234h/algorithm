public class 子数组的最大异或和 {
    public static void main(String[] args) {
        int[] arr = {3, -28, -29, 2};
        System.out.println(maxXorSubarray(arr));
    }

    private static class NumTrie {
        private final Node root = new Node();

        public void addNum(int num) {
            Node cur = root;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >>> i) & 1;
                if (cur.map[bit] == null) {
                    cur.map[bit] = new Node();
                }
                cur = cur.map[bit];
            }
        }

        public int maxXor(int num) {
            Node cur = root;
            int xor = num;
            for (int i = 31; i >= 0; i--) {
                int bit = (i == 31 ? 0 : 1) ^ ((num >>> i) & 1);
                if (cur.map[bit] == null) {
                    bit = 1 - bit;
                }
                cur = cur.map[bit];
                if (bit == 1) {
                    xor = xor ^ (1 << i);
                }
            }
            return xor;
        }
    }

    public static int maxXorSubarray(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        if (arr.length == 1) return arr[0];
        NumTrie trie = new NumTrie();
        int preXor = 0;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            trie.addNum(preXor);
            int xor = preXor ^ arr[i];
            preXor = xor;
            res = Math.max(res, trie.maxXor(xor));
        }
        return res;
    }

    private static class Node {
        Node[] map = new Node[2];
    }
}
