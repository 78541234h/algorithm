package learn.algorithm.binarytree;

import learn.algorithm.binarytree.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class 在二叉树中找到累加和为指定值的最长路径长度 {
    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(-3);
        root.left = new TreeNode<>(3);
        root.left.left = new TreeNode<>(1);
        root.left.right = new TreeNode<>(0);
        root.left.right.left = new TreeNode<>(1);
        root.left.right.right = new TreeNode<>(6);
        root.right = new TreeNode<>(-9);
        root.right.left = new TreeNode<>(2);
        root.right.right = new TreeNode<>(1);
        System.out.println(longestPath(root, 6));
    }

    public static int longestPath(TreeNode<Integer> root, int aim) {
        HashMap<Integer, Integer> sumToPathLenMap = new HashMap<>();
        sumToPathLenMap.put(0, 0);
        return process(root, 1, 0, aim, sumToPathLenMap);
    }

    public static int process(TreeNode<Integer> node, int len, int sum, int aim, Map<Integer, Integer> sumToPathLenMap) {
        if (node == null) {
            return 0;
        }
        sum += node.val;
        sumToPathLenMap.putIfAbsent(sum, len);
        int res = sumToPathLenMap.containsKey(sum - aim) ? len - sumToPathLenMap.get(sum - aim) : 0;
        int leftRes = process(node.left, len + 1, sum, aim, sumToPathLenMap);
        int rightRes = process(node.right, len + 1, sum, aim, sumToPathLenMap);
        if (sumToPathLenMap.get(sum) == len)
            sumToPathLenMap.remove(sum);
        return Math.max(res, Math.max(leftRes, rightRes));
    }
}
