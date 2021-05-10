package learn.algorithm.binarytree;

import learn.algorithm.binarytree.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class 判断一棵二叉树是否为搜索二叉树和完全二叉树 {
    public static void main(String[] args) {

    }

    public static boolean isBST(TreeNode<Integer> root) {
        if (root == null) return true;
        Stack<TreeNode<Integer>> stack = new Stack<>();
        TreeNode<Integer> cur = root;
        TreeNode<Integer> pre = null;
        while (!stack.empty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (pre != null && pre.val > cur.val)
                    return false;
                pre = cur;
                cur = cur.right;
            }
        }
        return true;
    }

    public static boolean isCBT(TreeNode<Integer> root) {
        if (root == null) return true;
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        TreeNode<Integer> node = root;
        queue.add(node);
        boolean isChildAllowed = true;
        while (!queue.isEmpty()) {
            node = queue.remove();
            if (node.left != null) {
                if (!isChildAllowed) return false;
                queue.add(node.left);
            } else {
                isChildAllowed = false;
            }

            if (node.right != null) {
                if (!isChildAllowed) return false;
                queue.add(node.right);
            } else {
                isChildAllowed = false;
            }
        }
        return true;
    }
}
