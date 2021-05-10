package learn.algorithm.binarytree;

import learn.algorithm.binarytree.common.TreeNode;

import java.util.HashMap;

public class 判断二叉树是否为平衡二叉树 {
    public static void main(String[] args) {

    }


    public static boolean isBalancedBinaryTree(TreeNode<Integer> t) {
        if (t == null) return true;
        HashMap<TreeNode<Integer>, Integer> map = new HashMap<>();
        map.put(null, 0);
        return check(t, map);
    }

    private static boolean check(TreeNode<Integer> node, HashMap<TreeNode<Integer>, Integer> map) {
        if (node == null) return true;
        if (!check(node.left, map) || !check(node.right, map))
            return false;
        Integer lh = map.get(node.left);
        Integer rh = map.get(node.right);
        if (Math.abs(lh - rh) < 2) {
            map.put(node, Math.max(lh, rh) + 1);
            return true;
        }
        return false;
    }
}
