public class 字典树的实现 {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("abc").insert("abcd").insert("abd").insert("b");
        System.out.println(trie.prefixNumber("c"));
    }


    private static class Node {
        int path = 0;
        int end = 0;
        Node[] map = null;
    }

    public static class Trie {
        private final Node root = new Node();

        Trie insert(String word) {
            Node cur = root;
            root.path++;
            int idx;
            for (int i = 0; i < word.length(); i++) {
                idx = word.charAt(i) - 'a';
                if (cur.map == null) {
                    cur.map = new Node[26];
                }
                if (cur.map[idx] == null) {
                    cur.map[idx] = new Node();
                }
                cur = cur.map[idx];
                cur.path++;
            }
            cur.end++;
            return this;
        }

        void delete(String word) {
            if (search(word)) {
                Node cur = root;
                root.path--;
                int idx;
                for (int i = 0; i < word.length(); i++) {
                    idx = word.charAt(i) - 'a';
                    if (cur.map[idx].path-- == 1) {
                        cur.map[idx] = null;
                        return;
                    }
                    cur = cur.map[idx];
                }
                cur.end--;
            }
        }

        boolean search(String word) {
            Node cur = root;
            int idx;
            for (int i = 0; i < word.length(); i++) {
                idx = word.charAt(i) - 'a';
                if (cur.map != null && cur.map[idx] != null) {
                    cur = cur.map[idx];
                } else {
                    return false;
                }
            }
            return cur.end > 0;
        }

        int prefixNumber(String pre) {
            if (pre == null || pre.length() == 0)
                return root.path;

            Node cur = root;
            int idx;
            for (int i = 0; i < pre.length(); i++) {
                idx = pre.charAt(i) - 'a';
                if (cur.map == null || cur.map[idx] == null) return 0;
                cur = cur.map[idx];
            }
            return cur.path;
        }
    }
}
