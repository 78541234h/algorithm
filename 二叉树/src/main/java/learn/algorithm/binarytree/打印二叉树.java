package learn.algorithm.binarytree;

import learn.algorithm.binarytree.common.TreeNode;

public class 打印二叉树 {
    public static void main(String[] args) {
        TreeNode<Integer> root = TreeNode.mock();
        printBinaryTree(root);
    }

    private static void printBinaryTree(TreeNode<Integer> root) {
        process(root, 0, "H");
    }

    private static void process(TreeNode<Integer> node, int level, String mark) {
        if (node == null) return;
        process(node.right, level + 1, "v");
        for (int i = 0; i < level; i++) {
            System.out.print("\t\t");
        }
        System.out.println(mark + node.val + mark);
        process(node.left, level + 1, "∧");
    }
}
