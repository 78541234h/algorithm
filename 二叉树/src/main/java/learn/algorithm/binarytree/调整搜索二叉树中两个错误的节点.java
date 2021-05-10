package learn.algorithm.binarytree;

import learn.algorithm.binarytree.common.TreeNode;

import java.util.Stack;

public class 调整搜索二叉树中两个错误的节点 {

    public static void main(String[] args) {

    }

    public static TreeNode<Integer>[] findErrors(TreeNode<Integer> root) {
        if (root == null) return null;
        if (root.left == null && root.right == null) return null;
        Stack<TreeNode<Integer>> stack = new Stack<>();
        TreeNode<Integer> nodeToPush = root;
        TreeNode<Integer> cur;
        TreeNode<Integer> last = null;
        boolean hasErr = false;
        TreeNode[] errs = new TreeNode[2];
        while (!stack.empty() || nodeToPush != null) {
            if (nodeToPush != null) {
                stack.push(nodeToPush);
                nodeToPush = nodeToPush.left;
            } else {
                cur = stack.pop();
                if (last != null && cur.val < last.val) {
                    hasErr = true;
                    errs[0] = errs[0] == null ? last : errs[0];
                    errs[1] = cur;
                }
                last = cur;
                nodeToPush = cur.right;
            }
        }
        return hasErr ? errs : null;
    }
}
