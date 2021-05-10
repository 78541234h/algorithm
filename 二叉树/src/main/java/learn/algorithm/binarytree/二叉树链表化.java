package learn.algorithm.binarytree;

import learn.algorithm.binarytree.common.TreeNode;

public class 二叉树链表化 {
    public static void main(String[] args) {
        TreeNode<Integer> mock = TreeNode.mock();
        TreeNode<Integer> head = convertToLinkedList(mock);
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.right;
        }
    }

    public static TreeNode<Integer> convertToLinkedList(TreeNode<Integer> root) {
        if (root == null) return null;
        return process(root).head;
    }

    private static Pair process(TreeNode<Integer> node) {
        if (node == null) return null;
        Pair res = new Pair(node, node);
        Pair left = process(node.left);
        Pair right = process(node.right);
        if (left != null) {
            res.head = left.head;
            left.tail.right = node;
            node.left = left.tail;
        }
        if (right != null) {
            res.tail = right.tail;
            node.right = right.head;
            right.head.left = node;
        }
        return res;
    }

    private static class Pair {
        TreeNode<Integer> head = null;
        TreeNode<Integer> tail = null;

        public Pair(TreeNode<Integer> head, TreeNode<Integer> tail) {
            this.head = head;
            this.tail = tail;
        }
    }

    public static void flatten(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode leftLast = cur.left;
                while (leftLast.right != null) {
                    leftLast = leftLast.right;
                }
                leftLast.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }

}
