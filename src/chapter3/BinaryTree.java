package chapter3;

import BasicDataStucture.BinaryTreeNode;
import BasicDataStucture.DefaultTreeNode;

import java.util.Stack;

import static chapter3.TreeUtil.add;

public class BinaryTree {


    public static <T> void visit(BinaryTreeNode<T> node) {
        System.out.print(node.value() + " ");
    }

    public static <T>  void preOrderRecur(BinaryTreeNode<T> root) {
        if (root != null) {
            visit(root);
            preOrderRecur(root.left());
            preOrderRecur(root.right());
        }
    }

    public static <T> void midOrderRecur(BinaryTreeNode<T> root) {
        if (root != null) {
            midOrderRecur(root.left());
            visit(root);
            midOrderRecur(root.right());
        }
    }

    public static <T> void postOrderRecur(BinaryTreeNode<T> root) {
        if (root != null) {
            postOrderRecur(root.left());
            postOrderRecur(root.right());
            visit(root);
        }
    }

    public static <T> void preOrder(BinaryTreeNode<T> root) {
        if (root != null) {
            Stack<BinaryTreeNode<T>> stack = new Stack<>();
            stack.push(root);
            while (!stack.empty()) {
                BinaryTreeNode<T> node = stack.pop();
                visit(node);
                if (node.right() != null)
                    stack.push(node.right());
                if (node.left() != null)
                    stack.push(node.left());
            }
        }
    }

    public static <T> void midOrder(BinaryTreeNode<T> root) {
        if (root != null) {
            Stack<BinaryTreeNode<T>> stack = new Stack<>();
            while (root != null || !stack.empty()) {
                if (root != null) {
                    stack.push(root);
                    root = root.left();
                } else {
                    BinaryTreeNode<T> node = stack.pop();
                    visit(node);
                    root = node.right();
                }
            }
        }
    }


    public static <T> void postOrderWithTwoStack(BinaryTreeNode<T> root) {
        if (root != null) {
            Stack<BinaryTreeNode<T>> s1 = new Stack<>();
            Stack<BinaryTreeNode<T>> s2 = new Stack<>();
            s1.push(root);
            while (!s1.empty()) {
                BinaryTreeNode<T> node = s1.pop();
                s2.push(node);
                if (node.left() != null)
                    s1.push(node.left());
                if (node.right() != null)
                    s2.push(node.right());
            }
            while (!s2.empty()) {
                visit(s2.pop());
            }
        }
    }

    public static <T> void postOrderWithOneStack(BinaryTreeNode<T> root) {
        if (root != null) {
            BinaryTreeNode<T> last  = null, peek = null;
            Stack<BinaryTreeNode<T>> s = new Stack<>();
            s.push(root);
            while (!s.empty()) {
                peek = s.peek();
                if (peek.left() != null && last != peek.left() && last != peek.right()) {
                    s.push(peek.left());
                } else if (peek.right() != null && last != peek.right()) {
                    s.push(peek.right());
                } else {
                    last = s.pop();
                    visit(last);
                }
            }
        }
    }

    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new DefaultTreeNode<>(6);
        add(root, 7);
        add(root, 4);
        add(root, 2);
        add(root, 5);
        add(root, 3);
        add(root, 1);
        postOrderWithOneStack(root);
    }

 
}
