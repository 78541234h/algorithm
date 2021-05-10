package learn.algorithm.binarytree;

import learn.algorithm.binarytree.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

import static learn.algorithm.binarytree.二叉树遍历.*;

public class 镜像二叉树 {
    public static void main(String[] args) {
        TreeNode<Integer> mock = TreeNode.mock();
        TreeNode<Integer> mirror = mirror(mock);
        midOrder(mirror, (Integer i) -> {
            System.out.print(i + " ");
        });
    }

    public static <T> TreeNode<T> mirrorRecursive(TreeNode<T> root) {
        if (root == null) return null;
        TreeNode<T> left = mirrorRecursive(root.left);
        TreeNode<T> right = mirrorRecursive(root.right);
        return new TreeNode<>(root.val, right, left);
    }


    public static <T> TreeNode<T> mirror(TreeNode<T> root) {
        if (root == null) return null;
        Queue<TreeNode<T>> origin = new LinkedList<>();
        Queue<TreeNode<T>> mirror = new LinkedList<>();
        origin.add(root);
        TreeNode<T> res = new TreeNode<>(root.val);
        mirror.add(res);
        while (!origin.isEmpty()) {
            TreeNode<T> node = origin.remove();
            TreeNode<T> copy = mirror.remove();
            if (node.left != null) {
                origin.add(node.left);
                copy.right = new TreeNode<>(node.left.val);
            }
            if (node.right != null) {
                origin.add(node.right);
                copy.left = new TreeNode<>(node.right.val);
            }
            if (copy.right != null) {
                mirror.add(copy.right);
            }
            if (copy.left != null) {
                mirror.add(copy.left);
            }
        }
        return res;
    }
}
