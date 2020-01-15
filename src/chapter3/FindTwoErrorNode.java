package chapter3;

import BasicDataStucture.BinaryTreeNode;

import java.util.Stack;

public class FindTwoErrorNode {
    public static void main(String[] args) {

    }


    public static <T extends Comparable<T>> BinaryTreeNode<T>[] getErrorNodes(BinaryTreeNode<T> root) {
        BinaryTreeNode<T>[] err = new BinaryTreeNode[2];
        BinaryTreeNode<T> cur = root, preVisit = null, pre = null;
        while (cur != null) {
            if ((pre = cur.left()) != null) {
                while (pre.right() != null && pre.right() != cur) {
                    pre = pre.right();
                }
                if (pre.right() == cur) {
                    pre.setRight(null);
                } else {
                    pre.setRight(cur);
                    cur = cur.left();
                    continue;
                }
            }
            if (preVisit != null && preVisit.value().compareTo(cur.value()) > 0) {
                if (err[0] == null) {
                    err[0] = preVisit;
                } else {
                    err[1] = cur;
                }
            }
            preVisit = cur;
            cur = cur.right();
        }
        return err;
    }

    public static <T extends Comparable<T>> void correctErrorNodes(BinaryTreeNode<T> root) {
        BinaryTreeNode<T>[] err = getErrorNodes(root);
        BinaryTreeNode<T>[] errParents = getParents(root, err);
        BinaryTreeNode<T> e1 = err[0], e2 = err[1], e1p = errParents[0], e2p = errParents[1];
        if (e1 == root) {
            if (e1 == e2p) {

            } else if()
        } else if (e2 == root) {
            if (e2 == e1p) {

            }
        } else {

        }
    }

    private static <T> BinaryTreeNode<T>[] getParents(BinaryTreeNode<T> root, BinaryTreeNode<T>[] err) {
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        BinaryTreeNode<T> head = root;
        BinaryTreeNode<T>[] par = new BinaryTreeNode[2];
        while (!stack.empty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left();
            } else {
                head = stack.pop();
                if (head.left() == err[0] || head.right() == err[0]) {
                    par[0] = head;
                }
                if (head.left() == err[1] || head.right() == err[1]) {
                    par[1] = head;
                }
                head = head.right();
            }
        }
    }
}
