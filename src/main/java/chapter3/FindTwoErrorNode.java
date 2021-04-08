package chapter3;

import BasicDataStucture.BinaryTreeNode;
import utils.PrintUtil;

import java.util.Stack;

public class FindTwoErrorNode {
    public static void main(String[] args) {
        test4();
    }

    private static void test1() {// swap root and its left child, and correct the tree
        BinaryTreeNode<Integer> root = TreeUtil.mockTree2();
        root.setValue(2);
        root.left().setValue(4);
        PrintUtil.print(root);
        PrintUtil.printSepreateLine();
        correctErrorNodes(root);
        PrintUtil.print(root);
    }

    private static void test2() {// swap two nodes without directly relation, and correct the tree
        BinaryTreeNode<Integer> root = TreeUtil.mockTree2();
        root.left().setValue(6);
        root.right().setValue(2);
        PrintUtil.print(root);
        PrintUtil.printSepreateLine();
        correctErrorNodes(root);
        PrintUtil.print(root);
    }

    private static void test3() {// swap root and an any node except root's children, and correct the tree
        BinaryTreeNode<Integer> root = TreeUtil.mockTree2();
        root.setValue(1);
        root.left().left().setValue(4);
        PrintUtil.print(root);
        PrintUtil.printSepreateLine();
        correctErrorNodes(root);
        PrintUtil.print(root);
    }

    private static void test4() {//swap a node and its' parent which is not root, and correct the tree
        BinaryTreeNode<Integer> root = TreeUtil.mockTree2();
        root.left().setValue(1);
        root.left().left().setValue(2);
        PrintUtil.print(root);
        PrintUtil.printSepreateLine();
        correctErrorNodes(root);
        PrintUtil.print(root);
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
                    pre.setRight(null);   //non-leaf node second visit
                } else {
                    pre.setRight(cur);   // non-leaf node first visit
                    cur = cur.left();
                    continue;
                }
            }
            if (preVisit != null && preVisit.value().compareTo(cur.value()) > 0) {
                err[0] = err[0] == null ? pre : err[0];  //be careful here
                err[1] = cur;
            }
            preVisit = cur;
            cur = cur.right();
        }
        return err;
    }

    public static <T extends Comparable<T>> void correctErrorNodes(BinaryTreeNode<T> root) {
        BinaryTreeNode<T>[] err = getErrorNodes(root);
        BinaryTreeNode<T>[] errParents = getParents(root, err);
        BinaryTreeNode<T> e1 = err[0], e2 = err[1], e1P = errParents[0], e2P = errParents[1];
        BinaryTreeNode<T> e1L = e1.left(), e1R = e1.right(), e2L = e2.left(), e2R = e2.right();
        // parents setting, using exclusive method
        if (!(e1 == e2P || e2 == root)) {
            if (e2P.left() == e2)
                e2P.setLeft(e1);
            else
                e2P.setRight(e1);
        }
        if (!(e2 == e1P || e1 == root)) {
            if (e1P.left() == e1)
                e1P.setLeft(e2);
            else
                e1P.setRight(e2);
        }
        //children setting
        e1.setLeft(e1 == e2L ? e2 : e2L);
        e1.setRight(e1 == e2R ? e2 : e2R);
        e2.setLeft(e2 == e1L ? e1 : e1L);
        e2.setRight(e2 == e2R ? e1 : e1R);
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
        return par;
    }
}