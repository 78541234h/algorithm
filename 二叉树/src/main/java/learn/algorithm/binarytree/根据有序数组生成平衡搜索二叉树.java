package learn.algorithm.binarytree;

import learn.algorithm.binarytree.common.TreeNode;

public class 根据有序数组生成平衡搜索二叉树 {
    public static void main(String[] args) {

    }

    public TreeNode<Integer> buildBST(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    private static TreeNode<Integer> process(int[] arr, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        TreeNode<Integer> node = new TreeNode<>(arr[mid]);
        TreeNode<Integer> left = process(arr, start, mid - 1);
        TreeNode<Integer> right = process(arr, mid + 1, end);
        node.left = left;
        node.right = right;
        return node;
    }
}
