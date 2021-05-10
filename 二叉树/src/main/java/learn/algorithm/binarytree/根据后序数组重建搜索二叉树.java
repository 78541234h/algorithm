package learn.algorithm.binarytree;

import learn.algorithm.binarytree.common.TreeNode;

public class 根据后序数组重建搜索二叉树 {
    public static void main(String[] args) {

    }

    public static boolean isPostArray(int[] arr) {
        if (arr == null || arr.length == 0) return false;
        if (arr.length < 4) return true;
        return check(arr, 0, arr.length - 1);
    }

    private static boolean check(int[] arr, int start, int end) {
        if (end - start < 3) return true;
        int rightStart = start;
        while (rightStart < end && arr[rightStart] < arr[end]) {
            rightStart++;
        }
        if (rightStart == end) {
            return check(arr, start, end - 1);
        } else {
            for (int i = rightStart + 1; i < end; i++) {
                if (arr[i] < arr[end])
                    return false;
            }
            return check(arr, start, rightStart - 1) && check(arr, rightStart, end - 1);
        }
    }

    public static TreeNode<Integer> rebuildBST(int[] postArr) {
        return process(postArr, 0, postArr.length);
    }

    private static TreeNode<Integer> process(int[] postArr, int start, int end) {
        if (start == end)
            return new TreeNode<>(postArr[start]);

        TreeNode<Integer> node = new TreeNode<>(postArr[end]);
        int rightStart = start;
        while (rightStart < end && postArr[rightStart] < postArr[end]) {
            rightStart++;
        }
        if (rightStart == end) {
            node.left = process(postArr, start, end - 1);
        } else if (rightStart == start) {
            node.right = process(postArr, start, end - 1);
        } else {
            node.left = process(postArr, start, rightStart - 1);
            node.right = process(postArr, rightStart, end - 1);
        }
        return node;
    }
}
