package chapter3;

import BasicDataStucture.BinaryTreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GetTreePathList {
    public static <T> List<List<BinaryTreeNode<T>>> getPaths(BinaryTreeNode<T> root) {
        LinkedList<BinaryTreeNode<T>> curPath = new LinkedList<>();
        List<List<BinaryTreeNode<T>>> paths = new LinkedList<>();
        getPathsRecur(root, curPath, paths);
        return paths;
    }

    public static <T> void getPathsRecur(BinaryTreeNode<T> head, LinkedList<BinaryTreeNode<T>> curPath, List<List<BinaryTreeNode<T>>> paths) {
        curPath.add(head);
        if (head.left() != null)
            getPathsRecur(head.left(), curPath, paths);
        if (head.right() != null)
            getPathsRecur(head.right(), curPath, paths);
        if (head.left() == null && head.right() == null)
            paths.add(new ArrayList<>(curPath));
        curPath.removeLast();
    }

    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = TreeUtil.mockTree1();
        PrintBianryTree.printTree(root);
        List<List<BinaryTreeNode<Integer>>> paths = getPaths(root);
        for (List<BinaryTreeNode<Integer>> path : paths) {
            for (BinaryTreeNode<Integer> node : path)
                System.out.print(node + " ");
            System.out.println();
        }
    }
}
