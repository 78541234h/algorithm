package learn.algorithm.binarytree;

public class 在二叉树中找到一个节点的后继节点 {
    public static class TreeNode {
        public TreeNode parent;
        public TreeNode left;
        public TreeNode right;
        public int val;

    }

    public static TreeNode successor(TreeNode node) {
        if (node == null) return null;
        TreeNode res = null;
        if (node.right != null) {
            res = node.right;
            while (res.left != null) {
                res = res.left;
            }
        } else {
            TreeNode cur = node;
            while (cur.parent != null) {
                if (cur.parent.left == cur) {
                    res = cur.parent;
                    break;
                }
                cur = cur.parent;
            }
        }
        return res;
    }
}
