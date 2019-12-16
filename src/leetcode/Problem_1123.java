package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-deepest-leaves/
 */

// 这两种方法都是自底向上的. 时间复杂度稳定在o(n), 是后序遍历
// 还可以自顶向下来做, 比较左子树和右子树的高度, 先序遍历, 这样每次都要多递归一次左右子树, 但从测试用例的角度来看, 该方法性能更好

public class Problem_1123 {
    public static void main(String[] args) {

    }

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        int depth = getDeapestNodeMap(root, map, 0);
        TreeNode res = getResult(root, map, depth);
        return res;
    }

    public int getDeapestNodeMap(TreeNode node, Map<Integer, Integer> map, int depth) {
        if (node == null)
            return depth - 1;
        int left = getDeapestNodeMap(node.left, map, depth + 1);
        int right = getDeapestNodeMap(node.right, map, depth + 1);
        int max = Math.max(left, right);
        if (depth == max)
            map.put(node.val, depth);
        return max;
    }

    public TreeNode getResult(TreeNode node, Map<Integer, Integer> map, int maxDepth) {
        if (node == null || (map.containsKey(node.val) && map.get(node.val) == maxDepth)) {
            return node;
        }
        TreeNode left = getResult(node.left, map, maxDepth);
        TreeNode right = getResult(node.right, map, maxDepth);
        if (left != null && right != null) {
            return node;
        }
        return left == null ? right : left;

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public TreeNode secondSolution(TreeNode node) {
        return subSolution(node, 0).node;
    }

    public ResultType subSolution(TreeNode node, int depth) {
        if (node == null)
            return new ResultType(null, depth - 1);
        ResultType left = subSolution(node.left, depth + 1);
        ResultType right = subSolution(node.right, depth + 1);
        if (left.depth == right.depth)
            return new ResultType(node, left.depth);
        return left.depth > right.depth ? left : right;
    }

    private class ResultType {
        TreeNode node;
        int depth;
        public ResultType(TreeNode p1, int p2) {
            node = p1;
            depth = p2;
        }
    }
}
