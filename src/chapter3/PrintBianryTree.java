package chapter3;

import java.util.Formatter;

public class PrintBianryTree {
    enum Type {
        LEFT, RIGHT, ROOT
    }

    static Formatter formatter = new Formatter(System.out);

    public static <T> void printTree(TreeNode<T> root) {
        print(root, 0, Type.ROOT);
    }

    private static <T> void print(TreeNode<T> head, int level, Type type) {
        if (head == null) return;
        print(head.rightChild, level + 1, Type.RIGHT);
        for (int i = 0; i != level; i++) {
            System.out.print("        ");
        }
        switch (type) {
            case LEFT:
                formatter.format("%-8s\n", "Λ" + head + "Λ");
                break;
            case RIGHT:
                formatter.format("%-8s\n", "V" + head + "V");
                break;
            case ROOT:
                formatter.format("%-8s\n", "H" + head + "H");
                break;
        }
        print(head.leftChild, level + 1, Type.LEFT);
    }

    public static void main(String[] args) {
        //TreeNode root = TreeUtil.mockTree1();
        //print(root, 0, Type.ROOT);
    }
}
