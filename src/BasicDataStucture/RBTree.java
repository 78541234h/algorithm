package BasicDataStucture;

import chapter3.PrintBianryTree;
import chapter3.TreeNode;

public class RBTree<T extends Comparable<T>> {
    public enum COLOR {
        BLACK, RED
    }

    static public class RBTreeNode<T extends Comparable<T>> extends TreeNode<T> {
        public COLOR color;
        public RBTreeNode<T> parent;

        public RBTreeNode(T value, COLOR color, RBTreeNode<T> parent, RBTreeNode<T> leftChild, RBTreeNode<T> rightChild) {
            super(value, leftChild, rightChild);
            this.color = color;
            this.parent = parent;
        }

        @Override
        public String toString() {

            return (color == COLOR.BLACK ? "B" : "R") + " " + value.toString();
        }
    }

    private RBTreeNode<T> root = null;

    private void leftRotate(RBTreeNode<T> n) {
        RBTreeNode<T> p = n.parent;
        p.rightChild = n.leftChild;
        n.leftChild = p;
        n.parent = p.parent;
        p.parent = n;
        if (n.parent == null) root = n;
        else {
            if (n.parent.leftChild == p) n.parent.leftChild = n;
            else {
                n.parent.rightChild = n;
            }
        }
    }

    private void rightRotate(RBTreeNode<T> n) {
        RBTreeNode<T> p = n.parent;
        p.leftChild = n.rightChild;
        n.rightChild = p;
        n.parent = p.parent;
        p.parent = n;
        if (n.parent == null) root = n;
        else {
            if (n.parent.leftChild == p) n.parent.leftChild = n;
            else {
                n.parent.rightChild = n;
            }
        }
    }

    private void modifyRotate(RBTreeNode<T> n) {
        RBTreeNode<T> p = n.parent;
        RBTreeNode<T> gp = p.parent;
        RBTreeNode<T> tmp;
        if (p.leftChild == n && gp.leftChild == p) {
            rightRotate(p);
        } else if (p.rightChild == n && gp.rightChild == p) {
            leftRotate(p);
        } else {
            tmp = p;
            p = n;
            n = tmp;
            if (n.rightChild == p && gp.leftChild == n) {
                leftRotate(p);
                rightRotate(p);
            } else {
                rightRotate(p);
                leftRotate(p);
            }
        }
    }

    public boolean add(T value) {
        if (root == null) {
            root = new RBTreeNode<T>(value, COLOR.BLACK, null, null, null);
        } else {
            RBTreeNode<T> p = root;
            RBTreeNode<T> gp, u, tmp;
            while (true) {
                if (p.value.compareTo(value) == 0) return false;
                tmp = (RBTreeNode<T>) (p.value.compareTo(value) > 0 ? p.leftChild : p.rightChild);
                if (tmp == null) break;
                p = tmp;
            }
            RBTreeNode<T> n = new RBTreeNode<>(value, COLOR.RED, p, null, null);
            if (p.value.compareTo(value) > 0) p.leftChild = n;
            else p.rightChild = n;
            while ((p = n.parent) != null && p.color == COLOR.RED) {
                gp = p.parent;
                u = (RBTreeNode<T>) (gp.leftChild == p ? gp.rightChild : gp.leftChild);
                if (u != null && u.color == COLOR.RED) {
                    // uncle is red, then uncle, parent, grandparent flip color
                    u.color = COLOR.BLACK;
                    p.color = COLOR.BLACK;
                    gp.color = gp == root ? COLOR.BLACK : COLOR.RED;
                    n = gp;             // continue loop
                } else {                // uncle is null or black then rotate <= 2 times, then exit
                    gp.color = COLOR.RED;
                    modifyRotate(n);
                    tmp = p.parent == n ? n : p;
                    tmp.color = COLOR.BLACK;
                    break;
                }
            }
        }
        return true;
    }

    public RBTreeNode<T> remove(T value) {
        return null;
    }

    public boolean check(RBTree<T> tree) {
        return false;
    }

    public static void main(String[] args) {
        RBTree<Integer> tree = new RBTree<>();
        for (int i = 11; i >= 1; i--) {
            tree.add(i);
        }
        PrintBianryTree.printTree(tree.root);
    }
}
