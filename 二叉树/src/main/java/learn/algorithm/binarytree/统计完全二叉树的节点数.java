package learn.algorithm.binarytree;

import learn.algorithm.binarytree.common.TreeNode;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class 统计完全二叉树的节点数 {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(2);
        root.left.left = new TreeNode<>(4);
        root.left.right = new TreeNode<>(5);
        root.right = new TreeNode<>(3);
        root.right.left = new TreeNode<>(6);
        System.out.print(countCBTreeNodes(root));
        Integer min = Integer.MIN_VALUE;
        System.out.println(min - 1);
        System.out.println(Integer.MAX_VALUE);
    }

    public static int countCBTreeNodes(TreeNode<Integer> head) {
        if (head == null) return 0;
        int leftHeight = height(head.left);
        int rightHeight = height(head.right);
        int res = 0;
        if (leftHeight == rightHeight) {
            res = (1 << leftHeight) + countCBTreeNodes(head.right);
        } else {
            res = countCBTreeNodes(head.left) + (1 << rightHeight);
        }
        return res;
    }

    public static int height(TreeNode<Integer> head) {
        int res = 0;
        while (head != null) {
            res++;
            head = head.left;
        }
        return res;
    }
}
