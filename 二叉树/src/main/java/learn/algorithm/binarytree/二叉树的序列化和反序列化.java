package learn.algorithm.binarytree;

import learn.algorithm.binarytree.common.TreeNode;

public class 二叉树的序列化和反序列化 {
    public static void main(String[] args) {
        TreeNode<Integer> root = TreeNode.mock();
        String str = serialize(root);
        System.out.println(str);
        TreeNode<Integer> copy = deserialize(str);
        System.out.println(serialize(copy));

    }

    public static String serialize(TreeNode<Integer> root) {
        StringBuilder builder = new StringBuilder();
        process(root, builder);
        return builder.toString();
    }

    private static void process(TreeNode<Integer> node, StringBuilder builder) {
        if (node == null) {
            builder.append("#!");
            return;
        }

        builder.append(node.val).append("!");
        process(node.left, builder);
        process(node.right, builder);
    }

    public static TreeNode<Integer> deserialize(String str) {
        String[] strs = str.split("!");
        int[] p = {-1};
        return process(strs, p);
    }

    private static TreeNode<Integer> process(String[] strs, int[] offset) {
        offset[0]++;
        if (strs[offset[0]].equals("#")) {
            return null;
        }
        TreeNode<Integer> node = new TreeNode<>(Integer.valueOf(strs[offset[0]]));
        node.left = process(strs, offset);
        node.right = process(strs, offset);
        return node;
    }
}
