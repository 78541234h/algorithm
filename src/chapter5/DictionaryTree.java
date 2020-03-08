package chapter5;

public class DictionaryTree {
    private TreeNode root;

    public DictionaryTree() {
        root = new TreeNode();
    }

    public void insert(String word) {
        if (word == null) return;
        char[] chas = word.toCharArray();
        TreeNode node = root;
        node.path++;
        for (int i = 0; i < chas.length; i++) {
            int index = chas[i] - 'a';
            if (node.set[index] == null) {
                node.set[index] = new TreeNode();
            }
            node = node.set[index];
            node.path++;
        }
        node.end++;
    }

    public void delete(String word) {
        if (search(word)) {
            char[] chas = word.toCharArray();
            TreeNode node = root;
            node.path--;
            for (int i = 0; i < word.length(); i++) {
                int index = chas[i] - 'a';
                node = node.set[index];
                node.path--;
            }
            node.end--;
        }
    }

    public boolean search(String word) {
        if (word == null) return false;
        char[] chas = word.toCharArray();
        TreeNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int index = chas[i] - 'a';
            if (node.set[index] == null) return false;
            node = node.set[index];
        }
        return node.end > 0;
    }

    public int prefixNum(String prefix) {
        if (prefix == null) return 0;
        char[] chas = prefix.toCharArray();
        TreeNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = chas[i] - 'a';
            if (node.set[index] == null) return 0;
            node = node.set[index];
        }
        return node.path;
    }

    public static void main(String[] args) {

    }


    private static class TreeNode {
        public int path;
        public int end;
        public TreeNode[] set;

        public TreeNode() {
            path = 0;
            end = 0;
            set = new TreeNode[26];
        }
    }
}
