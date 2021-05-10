package learn.algorithm.binarytree;

import learn.algorithm.binarytree.common.TreeNode;

import java.util.*;

public class 找到二叉树中符合搜索二叉树条件的最大拓扑结构 {
    public static void main(String[] args) {
        TreeNode<Integer> root = TreeNode.mock2();

        System.out.println(maxTopology2(root));
    }


    public static int maxTopology1(TreeNode<Integer> root) {
        if (root == null) return 0;
        int res = Math.max(process(root.left), process(root.right));
        res = Math.max(res, process(root));
        return res;
    }

    private static int process(TreeNode<Integer> root) {
        if (root == null) return 0;
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        int res = 0;
        TreeNode<Integer> node;
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (isBSTNode(root, node)) {
                res++;
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
        }
        return res;
    }

    private static boolean isBSTNode(TreeNode<Integer> root, TreeNode<Integer> node) {
        TreeNode<Integer> cur = root;
        while (cur != null) {
            if (cur.val > node.val) cur = cur.left;
            else if (cur.val < node.val) cur = cur.right;
            else return cur == node;
        }
        return false;
    }


    private static class Record {
        int left;
        int right;

        public Record(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public int sum() {
            return left + right + 1;
        }
    }

    public static int maxTopology2(TreeNode<Integer> root) {
        if (root == null) return 0;
        HashMap<TreeNode<Integer>, Record> records = new HashMap<>();
        return process(root, records);
    }

    private static int process(TreeNode<Integer> node, Map<TreeNode<Integer>, Record> records) {
        if (node == null)
            return 0;
        int res = Math.max(process(node.left, records), process(node.right, records));

        TreeNode<Integer> end = node.left;
        TreeNode<Integer> cur = node.left;
        int minus;
        //这里第一次写错了，第一次循环条件写成end != null && end.val < node.val。
        while (records.containsKey(end) && end.val < node.val) {
            end = end.right;
        }
        if (records.containsKey(end)) {
            minus = records.remove(end).sum();
            while (cur != end) {
                records.get(cur).right -= minus;
                cur = cur.right;
            }
        }

        cur = end = node.right;
        while (records.containsKey(end) && end.val > node.val) {
            end = end.left;
        }
        if (records.containsKey(end)) {
            minus = records.remove(end).sum();
            while (cur != end) {
                records.get(cur).left -= minus;
                cur = cur.left;
            }
        }
        //起初在外层方法里的records中放（null，new Record(0,0)），然后这里不对空检查，其实错了
        Record self = new Record(0, 0);
        Record left = records.getOrDefault(node.left, null);
        Record right = records.getOrDefault(node.right, null);
        self.left = left != null ? left.sum() : 0;
        self.right = right != null ? right.sum() : 0;
        records.put(node, self);
        return Math.max(res, self.sum());
    }
}
