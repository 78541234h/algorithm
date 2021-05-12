package learn.algorithm.binarytree;

import learn.algorithm.binarytree.common.TreeNode;

public class 二叉树节点间的最大距离问题 {
    public static void main(String[] args) {

    }

    private static class ReturnType {
        int height;
        int maxDistance;

        public ReturnType(int hight, int maxDistance) {
            this.height = hight;
            this.maxDistance = maxDistance;
        }

        public static final ReturnType nil = new ReturnType(0, 0);
    }

    public static int maxDistance(TreeNode<Integer> root) {
        return process(root).maxDistance;
    }

    private static ReturnType process(TreeNode<Integer> node) {
        if (node == null) return ReturnType.nil;
        ReturnType left = process(node.left);
        ReturnType right = process(node.right);
        int maxDistance = Math.max(left.maxDistance, right.maxDistance);
        maxDistance = Math.max(maxDistance, left.height + right.height + 1);
        int height = Math.max(left.height, right.height) + 1;
        return new ReturnType(height, maxDistance);
    }
}
