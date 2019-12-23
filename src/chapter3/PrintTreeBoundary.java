package chapter3;

import static chapter3.TreeUtil.mockTree1;

public class PrintTreeBoundary {
    public static void printTreeBoundary(TreeNode<Integer> root) {
        int height = getHeight(root);
        @SuppressWarnings("unchecked")
        TreeNode<Integer>[][] edges = new TreeNode[height][2];
        setEdges(edges, root, 0);
        for (int i = 0; i < height; i++) {
            System.out.print(edges[i][0].value + " ");
        }
        printLeaves(edges, root, 0);
        for (int i = height - 1; i >= 0; i--) {
            if (edges[i][1] != edges[i][0])
                System.out.print(edges[i][1].value + " ");
        }
    }

    private static int getHeight(TreeNode<Integer> root) {
        if (root == null) return 0;
        return Math.max(getHeight(root.leftChild), getHeight(root.rightChild)) + 1;
    }

    private static void setEdges(TreeNode<Integer>[][] edges, TreeNode<Integer> root, int level) {
        if (root == null) return;
        if (edges[level][0] == null) edges[level][0] = root;
        edges[level][1] = root;
        setEdges(edges, root.leftChild, level + 1);
        setEdges(edges, root.rightChild, level + 1);
    }

    private static void printLeaves(TreeNode<Integer>[][] edges, TreeNode<Integer> root, int level) {
        if (root == null) return;
        if (root.rightChild == null && root.leftChild == null
                && root != edges[level][0] && root != edges[level][1])
            System.out.print(root.value + " ");
        printLeaves(edges, root.leftChild, level + 1);
        printLeaves(edges, root.rightChild, level + 1);
    }


    public static void printTreeBoundary2(TreeNode<Integer> root) {
        if (root == null) return;
        System.out.print(root.value + " ");
        if (root.leftChild != null && root.rightChild != null) {
            printLeftEdge(root.leftChild, true);
            printRightEdge(root.rightChild, true);
        } else {
            if (root.leftChild != null)
                printTreeBoundary2(root.leftChild);
            if (root.rightChild != null)
                printTreeBoundary2(root.rightChild);
        }
    }

    private static void printLeftEdge(TreeNode<Integer> root, boolean print) {
        if (root == null) return;
        if (print || root.rightChild == null && root.leftChild == null)
            System.out.print(root.value + " ");
        printLeftEdge(root.leftChild, print);
        printLeftEdge(root.rightChild, print && root.leftChild == null);
    }

    private static void printRightEdge(TreeNode<Integer> root, boolean print) {
        if (root == null) return;
        printLeftEdge(root.leftChild, print && root.rightChild == null);
        printRightEdge(root.rightChild, print);
    }


    public static void main(String[] args) {
        TreeNode<Integer> root = mockTree1();
        printTreeBoundary(root);
    }
}
