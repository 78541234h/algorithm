package learn.algorithm.binarytree;

import learn.algorithm.binarytree.common.TreeNode;

import java.util.Stack;
import java.util.function.Consumer;

public class 二叉树遍历 {
    public static void main(String[] args) {
        TreeNode<Integer> root = TreeNode.mock();
        preOrder(root, (Integer i) -> {
            System.out.print(i + " ");
        });
        System.out.println("");
        midOrder(root, (Integer i) -> {
            System.out.print(i + " ");
        });
        System.out.println();
        postOrder(root, (Integer i) -> {
            System.out.print(i + " ");
        });
    }


    public static <T> void preOrderRecursive(TreeNode<T> node, Consumer<T> consumer) {
        if (node == null)
            return;
        consumer.accept(node.val);
        preOrderRecursive(node.left, consumer);
        preOrderRecursive(node.right, consumer);
    }

    public static <T> void midOrderRecursive(TreeNode<T> node, Consumer<T> consumer) {
        if (node == null)
            return;
        midOrderRecursive(node.left, consumer);
        consumer.accept(node.val);
        midOrderRecursive(node.right, consumer);
    }


    public static <T> void postOrderRecursive(TreeNode<T> node, Consumer<T> consumer) {
        if (node == null)
            return;
        postOrderRecursive(node.left, consumer);
        postOrderRecursive(node.right, consumer);
        consumer.accept(node.val);
    }


    public static <T> void preOrder(TreeNode<T> root, Consumer<T> consumer) {
        if (root == null) return;
        Stack<TreeNode<T>> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode<T> node = stack.pop();
            consumer.accept(node.val);
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
    }

    public static <T> void midOrder(TreeNode<T> root, Consumer<T> consumer) {
        if (root == null) return;
        Stack<TreeNode<T>> stack = new Stack<>();
        TreeNode<T> cur = root;
        while (!stack.empty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode<T> node = stack.pop();
                consumer.accept(node.val);
                cur = node.right;
            }
        }
    }

    public static <T> void postOrder(TreeNode<T> root, Consumer<T> consumer) {
        if (root == null) return;
        Stack<TreeNode<T>> stack = new Stack<>();
        TreeNode<T> pre = null;
        stack.push(root);
        while (!stack.empty()) {
            TreeNode<T> peek = stack.peek();
            //第一次写时漏条件了，peek.right != pre
            if (peek.left != null && peek.left != pre && peek.right != pre) {
                stack.push(peek.left);
            } else if (peek.right != null && pre == peek.left) {
                stack.push(peek.right);
            } else {
                TreeNode<T> node = stack.pop();
                consumer.accept(node.val);
                pre = node;
            }
        }
    }
}
