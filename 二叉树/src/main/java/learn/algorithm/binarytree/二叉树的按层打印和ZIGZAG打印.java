package learn.algorithm.binarytree;

import learn.algorithm.binarytree.common.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class 二叉树的按层打印和ZIGZAG打印 {
    public static void main(String[] args) {
        TreeNode<Integer> root = TreeNode.mock2();
        printByLevel(root);
    }

    public static void printByLevel(TreeNode<Integer> root) {
        if (root == null) return;
        TreeNode<Integer> curEnd = root;
        TreeNode<Integer> nextEnd = null;
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        int level = 1;
        System.out.print("Level 1 : ");
        while (!queue.isEmpty()) {
            TreeNode<Integer> node = queue.remove();
            System.out.print(node.val + " ");
            if (node.left != null) {
                nextEnd = node.left;
                queue.add(nextEnd);
            }
            if (node.right != null) {
                nextEnd = node.right;
                queue.add(nextEnd);
            }
            if (node == curEnd) {
                level++;
                curEnd = nextEnd;
                if(!queue.isEmpty())
                    System.out.print("\nLevel " + level + " : ");
            }
        }
    }


    public static void printByZigZag(TreeNode<Integer> root) {
        if (root == null) return;
        Deque<TreeNode<Integer>> deque = new LinkedList<>();
        boolean printFromLeft = true;
        TreeNode<Integer> curEnd = root;
        deque.addFirst(root);
        TreeNode<Integer> node;
        while (!deque.isEmpty()) {
            if (printFromLeft) {
                node = deque.removeFirst();
                if (node.left != null) {
                    deque.addLast(node.left);
                }
                if (node.right != null) {
                    deque.addLast(node.right);
                }
            } else {
                node = deque.removeLast();
                if (node.right != null)
                    deque.addFirst(node.right);
                if (node.left != null)
                    deque.addFirst(node.left);
            }
            System.out.print(node.val + " ");
            if (node == curEnd) {
                printFromLeft = !printFromLeft;
                if (printFromLeft)
                    curEnd = deque.peekLast();
                else curEnd = deque.peekFirst();
                System.out.println();
            }
        }
    }
}
