package chapter3;

import BasicDataStucture.BinaryTreeNode;
import BasicDataStucture.DefaultTreeNode;

public class Morris {
    public static void main(String[] args) {
        DefaultTreeNode<Integer> root = TreeUtil.mockTree2();
        PrintBianryTree.printTree(root);
        Morris m = new Morris();
        m.morris(root);
        System.out.print("\n");
        m.postOrderWalk(root);
    }

    private <T> BinaryTreeNode<T> predecessor(BinaryTreeNode<T> n) {
        BinaryTreeNode<T> pre = n.left();
        if (pre != null) {
            while (pre.right() != null && pre.right() != n) pre = pre.right();
        }
        return pre;
    }

    private <T> void morris(BinaryTreeNode<T> n) {
        BinaryTreeNode<T> pre = null;
        while (n != null) {
            while ((pre = predecessor(n)) != null && pre.right() != n) {
                pre.setRight(n);
                n = n.left();
            }
            visit(n);
            if (pre != null) pre.setRight(null);
            n = n.right();
        }
    }


    public <T> void visit(BinaryTreeNode<T> n) {
        System.out.print(n + " ");
    }

    public <T> void preOrderWalk(BinaryTreeNode<T> root) {
        BinaryTreeNode<T> head = root;
        BinaryTreeNode<T> pre = null;
        while (head != null) {
            pre = head.left();
            if (pre != null) {
                while (pre.right() != null && pre.right() != head) {
                    pre = pre.right();
                }
                if (pre.right() == null) {
                    visit(head);
                    pre.setRight(head);
                    head = head.left();
                    continue;
                } else {
                    pre.setRight(null);
                }
            } else {
                visit(head);
            }
            head = head.right();
        }
    }

    public <T> void midOrderWalk(BinaryTreeNode<T> root) {
        BinaryTreeNode<T> head = root;
        BinaryTreeNode<T> pre = null;
        while (head != null) {
            pre = head.left();
            if (pre != null) {
                while (pre.right() != null && pre.right() != head) {
                    pre = pre.right();
                }
                if (pre.right() == null) {
                    pre.setRight(head);
                    head = head.left();
                    continue;
                } else {
                    pre.setRight(null);
                }
            }
            visit(head);
            head = head.right();
        }
    }

    public <T> void postOrderWalk(BinaryTreeNode<T> root) {
        BinaryTreeNode<T> head = root;
        BinaryTreeNode<T> pre = null;
        while (head != null) {
            pre = head.left();
            if (pre != null) {
                while (pre.right() != null & pre.right() != head) {
                    pre = pre.right();
                }
                if (pre.right() == null) {
                    pre.setRight(head);
                    head = head.left();
                    continue;
                } else {
                    pre.setRight(null);
                    reverseRightEdge(head.left());
                    visitRightEdge(pre);
                    reverseRightEdge(pre);
                }
            }
            head = head.right();
        }
        head = reverseRightEdge(root);
        visitRightEdge(head);
        reverseRightEdge(head);
    }

    private <T> void visitRightEdge(BinaryTreeNode<T> from) {
        while (from != null) {
            visit(from);
            from = from.right();
        }
    }

    private <T> BinaryTreeNode<T> reverseRightEdge(BinaryTreeNode<T> from) {
        BinaryTreeNode<T> head = from;
        BinaryTreeNode<T> pre = null, next;
        while (head != null) {
            next = head.right();
            head.setRight(pre);
            pre = head;
            head = next;
        }
        return pre;
    }
}