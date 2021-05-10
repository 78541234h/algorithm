package learn.algorithm.binarytree;

import learn.algorithm.binarytree.common.TreeNode;

import java.awt.*;

public class 找到二叉树中最大的搜索二叉子树 {
    public static void main(String[] args) {
        TreeNode<Integer> root = TreeNode.mock2();

        TreeNode<Integer> res = maxSubSearchTree(root);
        System.out.println(res.val);
    }

    private static class Record {
        TreeNode<Integer> node;
        int maxVal;
        int minVal;
        int size;

        public Record(TreeNode<Integer> node, int maxVal, int minVal, int size) {
            this.node = node;
            this.maxVal = maxVal;
            this.minVal = minVal;
            this.size = size;
        }
    }


    public static TreeNode<Integer> maxSubSearchTree(TreeNode<Integer> root) {
        if (root == null)
            return null;
        return process(root).node;
    }

    private static Record process(TreeNode<Integer> node) {
        Record left;
        Record right;
        if (node.left != null && node.right != null) {
            left = process(node.left);
            right = process(node.right);
            if (left.node == node.left && right.node == node.right && left.maxVal < node.val && right.minVal > node.val)
                return new Record(node, right.maxVal, left.minVal, left.size + right.size + 1);
            return left.size > right.size ? left : right;
        } else if (node.left != null) {
            left = process(node.left);
            if (left.node == node.left && left.maxVal < node.val)
                return new Record(node, node.val, left.minVal, left.size + 1);
            return left;
        } else if (node.right != null) {
            right = process(node.right);
            if (right.node == node.right && right.minVal > node.val)
                return new Record(node, right.maxVal, node.val, right.size + 1);
            return right;
        } else {
            return new Record(node, node.val, node.val, 1);
        }
    }
}
