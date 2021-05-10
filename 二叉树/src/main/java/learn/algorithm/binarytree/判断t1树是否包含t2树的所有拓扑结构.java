package learn.algorithm.binarytree;

import learn.algorithm.binarytree.common.TreeNode;

public class 判断t1树是否包含t2树的所有拓扑结构 {
    public static void main(String[] args) {

    }


    public static boolean contains(TreeNode<Integer> t1, TreeNode<Integer> t2) {
        if (t2 == null) return true;
        if (t1 == null) return false;
        return check(t1, t2) || check(t1.left, t2) || check(t1.right, t2);
    }


    private static boolean check(TreeNode<Integer> t1, TreeNode<Integer> t2) {
        if (t2 == null) return true;
        if (t1 == null || !t1.val.equals(t2.val))
            return false;
        return check(t1.left, t2.left) && check(t1.right, t2.right);
    }
}
