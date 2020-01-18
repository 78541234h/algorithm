package chapter3;

import BasicDataStucture.BinaryTreeNode;
import chapter5.KMP;

public class SubTreeIdentical {
    public static void main(String[] args) {

    }

    //O(M*N)
    public static <T> boolean identicalOrNot(BinaryTreeNode<T> t1, BinaryTreeNode<T> t2) {
        if (t2 == null) return true;
        return check(t1, t2) || identicalOrNot(t1.left(), t2) || identicalOrNot(t1.right(), t2);
    }

    private static <T> boolean check(BinaryTreeNode<T> t1, BinaryTreeNode<T> t2) {
        if (t1 == null && t2 == null)
            return true;
        else if (t1 != null && t2 != null && t1.value() == t2.value()) {
            return check(t1.left(), t2.left()) && check(t1.right(), t2.right());
        } else {
            return false;
        }
    }

    //O(M+N)
    //first  Serialization then use KMP algorithm to compare the two String
    public static <T> boolean identicalOrNot1(BinaryTreeNode<T> t1, BinaryTreeNode<T> t2) {
        String s1 = getTreeString(t1);
        String s2 = getTreeString(t2);
        return KMP.getIndexOf(s1, s2) != -1;
    }


    private static <T> String getTreeString(BinaryTreeNode<T> t) {
        StringBuilder builder = new StringBuilder();
        getTreeString0(t, builder);
        return builder.toString();
    }

    private static <T> void getTreeString0(BinaryTreeNode<T> t, StringBuilder builder) {
        if (t == null) {
            builder.append("#!");
            return;
        }
        builder.append(t.value().toString());
        builder.append("!");
        getTreeString0(t.left(), builder);
        getTreeString0(t.right(), builder);
    }
}
