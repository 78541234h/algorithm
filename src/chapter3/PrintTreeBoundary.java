package chapter3;

import BasicDataStucture.BinaryTreeNode;
import BasicDataStucture.DefaultTreeNode;

import static chapter3.TreeUtil.mockTree1;

public class PrintTreeBoundary {
    public static void printTreeBoundary(BinaryTreeNode<Integer> root) {
        int height = getHeight(root);
        @SuppressWarnings("unchecked")
        BinaryTreeNode<Integer>[][] edges = new DefaultTreeNode[height][2];
        setEdges(edges, root, 0);
        for (int i = 0; i < height; i++) {
            System.out.print(edges[i][0].value() + " ");
        }
        printLeaves(edges, root, 0);
        for (int i = height - 1; i >= 0; i--) {
            if (edges[i][1] != edges[i][0])
                System.out.print(edges[i][1].value() + " ");
        }
    }

    private static int getHeight(BinaryTreeNode<Integer> root) {
        if (root == null) return 0;
        return Math.max(getHeight(root.left()), getHeight(root.right())) + 1;
    }

    private static void setEdges(BinaryTreeNode<Integer>[][] edges, BinaryTreeNode<Integer> root, int level) {
        if (root == null) return;
        if (edges[level][0] == null) edges[level][0] = root;
        edges[level][1] = root;
        setEdges(edges, root.left(), level + 1);
        setEdges(edges, root.right(), level + 1);
    }

    private static void printLeaves(BinaryTreeNode<Integer>[][] edges, BinaryTreeNode<Integer> root, int level) {
        if (root == null) return;
        if (root.right() == null && root.left() == null
                && root != edges[level][0] && root != edges[level][1])
            System.out.print(root.value() + " ");
        printLeaves(edges, root.left(), level + 1);
        printLeaves(edges, root.right(), level + 1);
    }


    public static void printTreeBoundary2(BinaryTreeNode<Integer> root) {
        if (root == null) return;
        System.out.print(root.value() + " ");
        if (root.left() != null && root.right() != null) {
            printLeftEdge(root.left(), true);
            printRightEdge(root.right(), true);
        } else {
            if (root.left() != null)
                printTreeBoundary2(root.left());
            if (root.right() != null)
                printTreeBoundary2(root.right());
        }
    }

    private static void printLeftEdge(BinaryTreeNode<Integer> root, boolean print) {
        if (root == null) return;
        if (print || root.right() == null && root.left() == null)
            System.out.print(root + " ");
        printLeftEdge(root.left(), print);
        printLeftEdge(root.right(), print && root.left() == null);
    }

    private static void printRightEdge(BinaryTreeNode<Integer> root, boolean print) {
        if (root == null) return;
        printLeftEdge(root.left(), print && root.right() == null);
        printRightEdge(root.right(), print);
    }


    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = mockTree1();
        printTreeBoundary(root);
    }
}
