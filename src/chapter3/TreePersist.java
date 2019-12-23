package chapter3;

import java.util.LinkedList;
import java.util.Queue;

public class TreePersist {

    public static String persistTree(TreeNode<Integer> root) {
        if (root == null) return null;
        StringBuilder builder = new StringBuilder();
        persist(root, builder);
        return builder.toString();
    }

    private static void persist(TreeNode<Integer> root, StringBuilder builder) {
        if (root == null) {
            builder.append("#!");
            return;
        }
        builder.append(root.value);
        builder.append("!");
        persist(root.leftChild, builder);
        persist(root.rightChild, builder);
    }

    public static TreeNode<Integer> recoverTree(String str) {
        if (str == null) return null;
        String[] vals = str.split("!");
        Queue<String> queue = new LinkedList<>();
        for (String s : vals) {
            queue.offer(s);
        }
        return recover(queue);
    }

    private static TreeNode<Integer> recover(Queue<String> queue) {
        if (queue.isEmpty()) {
            throw new RuntimeException("source is damage!");
        }
        String val = queue.poll();
        if (val.equals("#")) return null;
        TreeNode<Integer> node = new TreeNode<>(Integer.valueOf(val));
        node.leftChild = recover(queue);
        node.rightChild = recover(queue);
        return node;
    }

    public static <T> String persistByLevel(TreeNode<T> root) {
        if (root == null) return null;
        Queue<TreeNode<T>> queue = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        builder.append(root.value);
        builder.append("!");
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode<T> node = queue.poll();
            if (node.leftChild == null)
                builder.append("#!");
            else {
                builder.append(node.leftChild.value);
                builder.append("!");
                queue.offer(node.leftChild); //第一次写这里没有加
            }
            if (node.rightChild == null)
                builder.append("#!");
            else {
                builder.append(node.rightChild.value);
                builder.append("!");
                queue.offer(node.rightChild);
            }
        }
        return builder.toString();
    }

    public static TreeNode<Integer> recoverByLevel(String str) {
        if (str == null) return null;
        String[] arr = str.split("!"); //第一次用了queue来保存这些元素, 其实没必要, 一个arr就好
        int index = 0;
        Queue<TreeNode<Integer>> nodes = new LinkedList<>();
        TreeNode<Integer> root = new TreeNode<>(Integer.valueOf(arr[index++]));
        nodes.offer(root);
        while (!nodes.isEmpty()) {
            TreeNode<Integer> head = nodes.poll();
            String left = arr[index++];
            String right = arr[index++];
            if (!left.equals("#")) {
                head.leftChild = new TreeNode<>(Integer.valueOf(left));
                nodes.offer(head.leftChild);
            }
            if (!right.equals("#")) {
                head.rightChild = new TreeNode<>(Integer.valueOf(right));
                nodes.offer(head.rightChild);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode<Integer> node = TreeUtil.mockTree1();
        String tree = persistTree(node);
        System.out.println(tree);
        TreeNode<Integer> recover = recoverTree(tree);
        PrintBianryTree.printTree(recover);
        System.out.println("===============================================================");
        tree = persistByLevel(node);
        System.out.println(tree);
        recover = recoverByLevel(tree);
        PrintBianryTree.printTree(recover);
    }


}
