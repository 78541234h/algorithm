package learn.algorithm.binarytree.common;


public class TreeNode<T> {
    public TreeNode<T> left;
    public TreeNode<T> right;
    public T val;

    public TreeNode(T val) {
        left = null;
        right = null;
        this.val = val;
    }

    public TreeNode(T val, TreeNode<T> left, TreeNode<T> right) {
        this.left = left;
        this.right = right;
        this.val = val;
    }


    public static TreeNode<Integer> mock() {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(2);
        root.left.left = new TreeNode<>(4);
        root.left.right = new TreeNode<>(5);
        root.left.right.left = new TreeNode<>(8);
        root.right = new TreeNode<>(3);
        root.right.left = new TreeNode<>(6);
        root.right.right = new TreeNode<>(7);
        root.right.right.left = new TreeNode<>(9);
        root.right.right.right = new TreeNode<>(10);
        return root;
    }

    public static TreeNode<Integer> mock2() {
        TreeNode<Integer> root = new TreeNode<>(6);
        root.left = new TreeNode<>(1);
        root.left.left = new TreeNode<>(0);
        root.left.right = new TreeNode<>(3);
        root.right = new TreeNode<>(12);
        root.right.left = new TreeNode<>(10);
        root.right.left.left = new TreeNode<>(4);
        root.right.left.left.left = new TreeNode<>(2);
        root.right.left.left.right = new TreeNode<>(5);
        root.right.left.right = new TreeNode<>(14);
        root.right.left.right.left = new TreeNode<>(11);
        root.right.left.right.right = new TreeNode<>(15);
        root.right.right = new TreeNode<>(13);
        root.right.right.left = new TreeNode<>(20);
        root.right.right.right = new TreeNode<>(16);
        return root;
    }

    public static TreeNode<Integer> mock3() {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(2);
        root.left.left = new TreeNode<>(4);
        root.left.right = new TreeNode<>(5);
        root.left.left.right = new TreeNode<>(8);
        root.left.right.left = new TreeNode<>(9);
        root.right = new TreeNode<>(3);
        root.right.left = new TreeNode<>(6);
        root.right.right = new TreeNode<>(7);
        return root;
    }


    public static TreeNode<Integer> mock4() {
        TreeNode<Integer> root = root = new TreeNode<>(2);
        root.left = new TreeNode<>(4);
        root.right = new TreeNode<>(5);
        root.left.right = new TreeNode<>(8);
        root.right.left = new TreeNode<>(9);
        return root;
    }

    public static TreeNode<Integer> mock5() {
        TreeNode<Integer> root = root = new TreeNode<>(2);
        root.left = new TreeNode<>(4);
        root.right = new TreeNode<>(5);
        root.left.right = new TreeNode<>(8);
        return root;
    }
}
