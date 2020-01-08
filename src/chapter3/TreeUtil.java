package chapter3;

import BasicDataStucture.BinaryTreeNode;
import BasicDataStucture.DefaultTreeNode;

class TreeUtil {
    static <T extends Comparable<T>> void add(BinaryTreeNode<T> root, T value) {
        while (true) {
            if (root.value() == value || root.value().equals(value))
                return;
            else if (root.value().compareTo(value) > 0) {
                if (root.left() == null)
                    root.setLeft(new DefaultTreeNode<T>(value));
                else
                    root = root.left();
            } else {
                if (root.right() == null)
                    root.setRight(new DefaultTreeNode<T>(value));
                else
                    root = root.right();
            }
        }
    }

    static DefaultTreeNode<Integer> mockTree2() {
        DefaultTreeNode<Integer> root = new DefaultTreeNode<>(4);
        root.setLeft(new DefaultTreeNode<>(2));
        root.left().setLeft(new DefaultTreeNode<>(1));
        root.left().setRight(new DefaultTreeNode<>(3));
        root.setRight(new DefaultTreeNode<>(6));
        root.right().setLeft(new DefaultTreeNode<>(5));
        root.right().setRight(new DefaultTreeNode<>(7));
        return root;
    }

    static DefaultTreeNode<Integer> mockTree1() {
        DefaultTreeNode<Integer> root = new DefaultTreeNode<>(1);
        root.setLeft(new DefaultTreeNode<>(200000));
        root.left().setRight(new DefaultTreeNode<>(4));
        root.left().right().setLeft(new DefaultTreeNode<>(7));
        root.left().right().setRight(new DefaultTreeNode<>(8));
        root.left().right().right().setRight(new DefaultTreeNode<>(11));
        root.left().right().right().right().setLeft(new DefaultTreeNode<>(13));
        root.left().right().right().right().setRight(new DefaultTreeNode<>(14));

        root.setRight(new DefaultTreeNode<>(3));
        root.right().setLeft(new DefaultTreeNode<>(5));
        root.right().setRight(new DefaultTreeNode<>(6));
        root.right().left().setLeft(new DefaultTreeNode<>(9));
        root.right().left().setRight(new DefaultTreeNode<>(10));
        root.right().left().left().setLeft(new DefaultTreeNode<>(12));
        root.right().left().left().left().setLeft(new DefaultTreeNode<>(15));
        root.right().left().left().left().setRight(new DefaultTreeNode<>(16));

        return root;
    }
}
