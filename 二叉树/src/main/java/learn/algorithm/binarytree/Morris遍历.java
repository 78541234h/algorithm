package learn.algorithm.binarytree;

import learn.algorithm.binarytree.common.TreeNode;

import java.util.function.Consumer;

public class Morris遍历 {
    public static void main(String[] args) {
        TreeNode<Integer> root = TreeNode.mock();
        morrisPost(root, System.out::println);
    }

    public static <T> void morrisPre(TreeNode<T> root, Consumer<T> consumer) {
        TreeNode<T> cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode<T> leftLast = cur.left;
                while (leftLast.right != null && leftLast.right != cur)
                    leftLast = leftLast.right;
                if (leftLast.right == null) {
                    leftLast.right = cur;
                } else {
                    leftLast.right = null;
                    cur = cur.right;
                    continue;
                }
            }
            consumer.accept(cur.val);
            cur = cur.left != null ? cur.left : cur.right;
        }
    }

    public static <T> void morrisMid(TreeNode<T> root, Consumer<T> consumer) {
        TreeNode<T> cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode<T> leftLast = cur.left;
                while (leftLast.right != null && leftLast.right != cur) {
                    leftLast = leftLast.right;
                }
                if (leftLast.right == null) {
                    leftLast.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    leftLast.right = null;
                }
            }
            consumer.accept(cur.val);
            cur = cur.right;
        }
    }

    public static <T> void morrisPost(TreeNode<T> root, Consumer<T> consumer) {
        TreeNode<T> cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode<T> leftLast = cur.left;
                while (leftLast.right != null && leftLast.right != cur) {
                    leftLast = leftLast.right;
                }
                if (leftLast.right == null) {
                    leftLast.right = cur;
                } else {
                    leftLast.right = null;
                    consumeRightBound(cur.left, consumer);
                    cur = cur.right;
                    continue;
                }
            }
            cur = cur.left != null ? cur.left : cur.right;
        }
        consumeRightBound(root, consumer);
    }

    private static <T> void consumeRightBound(TreeNode<T> node, Consumer<T> consumer) {
        TreeNode<T> pre = null;
        TreeNode<T> cur = node;
        TreeNode<T> next;
        while (cur != null) {
            next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
        cur = pre;
        pre = null;
        while (cur != null) {
            consumer.accept(cur.val);
            next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
    }
}