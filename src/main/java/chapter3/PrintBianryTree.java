package chapter3;

import BasicDataStucture.BinaryTreeNode;
import BasicDataStucture.DefaultTreeNode;
import utils.PrintUtil;

import java.util.Deque;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.Queue;

public class PrintBianryTree {
    enum Type {
        LEFT, RIGHT, ROOT
    }

    static Formatter formatter = new Formatter(System.out);

    public static <T> void printTree(BinaryTreeNode<T> root) {
        print(root, 0, Type.ROOT);
    }

    private static <T> void print(BinaryTreeNode<T> head, int level, Type type) {
        if (head == null) return;
        print(head.right(), level + 1, Type.RIGHT);
        for (int i = 0; i != level; i++) {
            System.out.print("        ");
        }
        switch (type) {
            case LEFT:
                formatter.format("%-8s\n", "Λ" + head + "Λ");
                break;
            case RIGHT:
                formatter.format("%-8s\n", "V" + head + "V");
                break;
            case ROOT:
                formatter.format("%-8s\n", "H" + head + "H");
                break;
        }
        print(head.left(), level + 1, Type.LEFT);
    }

    public static <T> void printTreeByLevel(BinaryTreeNode<T> root) {
        if (root != null) {
            Queue<BinaryTreeNode<T>> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                BinaryTreeNode<T> node = queue.poll();
                System.out.print(node + " ");
                if (node.left() != null)
                    queue.offer(node.left());
                if (node.right() != null)
                    queue.offer(node.right());
            }
        }
    }

    public static <T> void printTreeByZigZag(BinaryTreeNode<T> root, boolean leftToRight) {
        if (root != null) {
            Deque<BinaryTreeNode<T>> dq = new LinkedList<>();
            dq.offer(root);
            BinaryTreeNode<T> cur = null, curTail = null, nextTail = null;
            curTail = root;
            while (!dq.isEmpty()) {
                if (leftToRight) {
                    cur = dq.pollFirst();
                    if (cur.left() != null) dq.offerLast(cur.left());
                    if (cur.right() != null) dq.offerLast(cur.right());

                } else {
                    cur = dq.pollLast();
                    if (cur.right() != null) dq.offerFirst(cur.right());
                    if (cur.left() != null) dq.offerFirst(cur.left());
                }
                if (nextTail == null) {
                    if (leftToRight) {
                        nextTail = cur.left() == null ? cur.right() : cur.left();
                    } else {
                        nextTail = cur.right() == null ? cur.left() : cur.right();
                    }
                }
                PrintUtil.printElement(cur);
                if (cur == curTail && !dq.isEmpty()) {
                    leftToRight = !leftToRight;
                    curTail = nextTail;
                    nextTail = null;
                    PrintUtil.newLine();
                }
            }
        }
    }

    public static void main(String[] args) {
        DefaultTreeNode<Integer> root = TreeUtil.mockTree1();
        print(root, 0, Type.ROOT);
        PrintUtil.printSepreateLine();
        printTreeByZigZag(root, true);
    }
}
