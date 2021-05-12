package learn.algorithm.binarytree;

import learn.algorithm.binarytree.common.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class 统计和生成所有不同的二叉树 {

    public static void main(String[] args) {
        for (int i = 10; i >= 0; i--) {
            int res1 = numTrees(i);
            System.out.println(res1);
        }
    }

    public static int numTrees(int n) {
        if (n <= 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 5;
        int mid = (n + 1) / 2;
        int res = 0;
        for (int i = n - 1; i >= mid; i--) {
            res += numTrees(i) * numTrees(n - 1 - i) * 2;
        }
        if (n % 2 == 1) {
            int tmp = numTrees(mid - 1);
            res += tmp * tmp;
        }
        return res;
    }

    public static List<TreeNode<Integer>> generateTrees(int n) {
        return generate(1, n);
    }

    public static List<TreeNode<Integer>> generate(int start, int end) {
        LinkedList<TreeNode<Integer>> list = new LinkedList<>();
        if (start > end) {
            list.add(null);
        } else if (start == end) {
            list.add(new TreeNode<>(start));
        } else {
            for (int i = start; i < end; i++) {
                TreeNode<Integer> head = new TreeNode<>(i);
                List<TreeNode<Integer>> lefts = generate(start, i - 1);
                List<TreeNode<Integer>> rights = generate(i + 1, end);
                for (TreeNode<Integer> left : lefts) {
                    for (TreeNode<Integer> right : rights) {
                        head.left = left;
                        head.right = right;
                        list.add(clone(head));
                    }
                }
            }
        }
        return list;
    }

    public static TreeNode<Integer> clone(TreeNode<Integer> node) {
        if (node == null) return null;
        TreeNode<Integer> clone = new TreeNode<>(node.val);
        clone.left = clone(node.left);
        clone.right = clone(node.right);
        return clone;
    }
}
