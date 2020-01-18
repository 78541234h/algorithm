package chapter3;

import BasicDataStucture.BinaryTreeNode;

public class JudgeContainedOrNot {
    public static void main(String[] args) {

    }

    public static <T> boolean containedOrNot(BinaryTreeNode<T> t1, BinaryTreeNode<T> t2) {
        if (t2 == null)
            return true;
        if (t1 == null)
            return false;
        return check(t1, t2) || containedOrNot(t1.left(), t2) || containedOrNot(t1.right(), t2);
    }

    private static <T> boolean check(BinaryTreeNode<T> t1, BinaryTreeNode<T> t2) {
        if (t2 == null) return true;
        if (t1 == null || t1.value() != t2.value())
            return false;
        return check(t1.left(), t2.left()) && check(t1.right(), t2.right());
    }
}
