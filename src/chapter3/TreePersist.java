package chapter3;

import BasicDataStucture.BinaryTreeNode;
import BasicDataStucture.DefaultTreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class TreePersist {

    public static String persistTree(BinaryTreeNode<Integer> root) {
        if (root == null) return null;
        StringBuilder builder = new StringBuilder();
        persist(root, builder);
        return builder.toString();
    }

    private static void persist(BinaryTreeNode<Integer> root, StringBuilder builder) {
        if (root == null) {
            builder.append("#!");
            return;
        }
        builder.append(root.value());
        builder.append("!");
        persist(root.left(), builder);
        persist(root.right(), builder);
    }

    public static BinaryTreeNode<Integer> recoverTree(String str) {
        if (str == null) return null;
        String[] vals = str.split("!");
        Queue<String> queue = new LinkedList<>();
        for (String s : vals) {
            queue.offer(s);
        }
        return recover(queue);
    }

    private static BinaryTreeNode<Integer> recover(Queue<String> queue) {
        if (queue.isEmpty()) {
            throw new RuntimeException("source is damage!");
        }
        String val = queue.poll();
        if (val.equals("#")) return null;
        BinaryTreeNode<Integer> node = new DefaultTreeNode<>(Integer.valueOf(val));
        node.setLeft(recover(queue));
        node.setRight(recover(queue));
        return node;
    }

    public static <T> String persistByLevel(BinaryTreeNode<T> root) {
        if (root == null) return null;
        Queue<BinaryTreeNode<T>> queue = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        builder.append(root.value());
        builder.append("!");
        queue.offer(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode<T> node = queue.poll();
            if (node.left() == null)
                builder.append("#!");
            else {
                builder.append(node.left().value());
                builder.append("!");
                queue.offer(node.left()); //第一次写这里没有加
            }
            if (node.right() == null)
                builder.append("#!");
            else {
                builder.append(node.right().value());
                builder.append("!");
                queue.offer(node.right());
            }
        }
        return builder.toString();
    }

    public static BinaryTreeNode<Integer> recoverByLevel(String str) {
        if (str == null) return null;
        String[] arr = str.split("!"); //第一次用了queue来保存这些元素, 其实没必要, 一个arr就好
        int index = 0;
        Queue<BinaryTreeNode<Integer>> nodes = new LinkedList<>();
        BinaryTreeNode<Integer> root = new DefaultTreeNode<>(Integer.valueOf(arr[index++]));
        nodes.offer(root);
        while (!nodes.isEmpty()) {
            BinaryTreeNode<Integer> head = nodes.poll();
            String left = arr[index++];
            String right = arr[index++];
            if (!left.equals("#")) {
                head.setLeft(new DefaultTreeNode<>(Integer.valueOf(left)));
                nodes.offer(head.left());
            }
            if (!right.equals("#")) {
                head.setRight(new DefaultTreeNode<>(Integer.valueOf(right)));
                nodes.offer(head.right());
            }
        }
        return root;
    }

    public static void main(String[] args) {
        BinaryTreeNode<Integer> node = TreeUtil.mockTree1();
        String tree = persistTree(node);
        System.out.println(tree);
        BinaryTreeNode<Integer> recover = recoverTree(tree);
        PrintBianryTree.printTree(recover);
        System.out.println("===============================================================");
        tree = persistByLevel(node);
        System.out.println(tree);
        recover = recoverByLevel(tree);
        PrintBianryTree.printTree(recover);
    }


}
