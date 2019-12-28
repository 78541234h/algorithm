package chapter3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GetTreePathList {
    public static <T> List<List<TreeNode<T>>> getPaths(TreeNode<T> root) {
        LinkedList<TreeNode<T>> curPath = new LinkedList<>();
        List<List<TreeNode<T>>> paths = new LinkedList<>();
        getPathsRecur(root, curPath, paths);
        return paths;
    }

    public static <T> void getPathsRecur(TreeNode<T> head, LinkedList<TreeNode<T>> curPath, List<List<TreeNode<T>>> paths) {
        curPath.add(head);
        if (head.leftChild != null)
            getPathsRecur(head.leftChild, curPath, paths);
        if (head.rightChild != null)
            getPathsRecur(head.rightChild, curPath, paths);
        if (head.leftChild == null && head.rightChild == null)
            paths.add(new ArrayList<>(curPath));
        curPath.removeLast();
    }

    public static void main(String[] args) {
        TreeNode<Integer> root = TreeUtil.mockTree1();
        PrintBianryTree.printTree(root);
        List<List<TreeNode<Integer>>> paths = getPaths(root);
        for (List<TreeNode<Integer>> path : paths) {
            for (TreeNode<Integer> node : path)
                System.out.print(node + " ");
            System.out.println();
        }
    }
}
