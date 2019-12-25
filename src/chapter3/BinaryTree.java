package chapter3;

import java.util.Stack;

import static chapter3.TreeUtil.add;

public class BinaryTree {


    public static <T> void visit(TreeNode<T> node) {
        System.out.print(node.value + " ");
    }

    public static <T>  void preOrderRecur(TreeNode<T> root) {
        if (root != null) {
            visit(root);
            preOrderRecur(root.leftChild);
            preOrderRecur(root.rightChild);
        }
    }

    public static <T> void midOrderRecur(TreeNode<T> root) {
        if (root != null) {
            midOrderRecur(root.leftChild);
            visit(root);
            midOrderRecur(root.rightChild);
        }
    }

    public static <T> void postOrderRecur(TreeNode<T> root) {
        if (root != null) {
            postOrderRecur(root.leftChild);
            postOrderRecur(root.rightChild);
            visit(root);
        }
    }

    public static <T> void preOrder(TreeNode<T> root) {
        if (root != null) {
            Stack<TreeNode<T>> stack = new Stack<>();
            stack.push(root);
            while (!stack.empty()) {
                TreeNode<T> node = stack.pop();
                visit(node);
                if (node.rightChild != null)
                    stack.push(node.rightChild);
                if (node.leftChild != null)
                    stack.push(node.leftChild);
            }
        }
    }

    public static <T> void midOrder(TreeNode<T> root) {
        if (root != null) {
            Stack<TreeNode<T>> stack = new Stack<>();
            while (root != null || !stack.empty()) {
                if (root != null) {
                    stack.push(root);
                    root = root.leftChild;
                } else {
                    TreeNode<T> node = stack.pop();
                    visit(node);
                    root = node.rightChild;
                }
            }
        }
    }


    public static <T> void postOrderWithTwoStack(TreeNode<T> root) {
        if (root != null) {
            Stack<TreeNode<T>> s1 = new Stack<>();
            Stack<TreeNode<T>> s2 = new Stack<>();
            s1.push(root);
            while (!s1.empty()) {
                TreeNode<T> node = s1.pop();
                s2.push(node);
                if (node.leftChild != null)
                    s1.push(node.leftChild);
                if (node.rightChild != null)
                    s2.push(node.rightChild);
            }
            while (!s2.empty()) {
                visit(s2.pop());
            }
        }
    }

    public static <T> void postOrderWithOneStack(TreeNode<T> root) {
        if (root != null) {
            TreeNode<T> last  = null, peek = null;
            Stack<TreeNode<T>> s = new Stack<>();
            s.push(root);
            while (!s.empty()) {
                peek = s.peek();
                if (peek.leftChild != null && last != peek.leftChild && last != peek.rightChild) {
                    s.push(peek.leftChild);
                } else if (peek.rightChild != null && last != peek.rightChild) {
                    s.push(peek.rightChild);
                } else {
                    last = s.pop();
                    visit(last);
                }
            }
        }
    }

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(6);
        add(root, 7);
        add(root, 4);
        add(root, 2);
        add(root, 5);
        add(root, 3);
        add(root, 1);
        postOrderWithOneStack(root);
    }

 
}
