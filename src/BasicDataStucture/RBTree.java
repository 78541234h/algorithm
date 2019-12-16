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
            while (true) {
                if (p.value.compareTo(value) == 0) return false;
                RBTreeNode<T> tmp = (RBTreeNode<T>) (p.value.compareTo(value) > 0 ? p.leftChild : p.rightChild);
                if (tmp == null) break;
                p = tmp;
            }
            RBTreeNode<T> n = new RBTreeNode<>(value, COLOR.RED, p, null, null);
            if (p.value.compareTo(value) > 0) p.leftChild = n;
            else {
                p.rightChild = n;
            }
            if (p.color == COLOR.RED) {
                modifyRotate(n);
                if (n.parent == p) {
                    n.color = COLOR.BLACK;
                    n = p;
                } else p.color = COLOR.BLACK;
                if (n == root) {
                    n.color = COLOR.BLACK;
                } else {
                    RBTreeNode<T> gp;
                    while ((p = n.parent).color == COLOR.RED) {     // RED
                        gp = p.parent;
                        RBTreeNode<T> u = (RBTreeNode<T>) (gp.leftChild == p ? gp.rightChild : gp.leftChild);
                        gp.color = COLOR.RED;
                        if (u.color == COLOR.RED) {
                            p.color = COLOR.BLACK;
                            u.color = COLOR.BLACK;
                            if (gp == root) gp.color = COLOR.BLACK;
                            else n = gp;
                        } else {
                            modifyRotate(n);
                            if (n.parent == p) p.color = COLOR.BLACK;
                            else n.color = COLOR.BLACK;
                            break;
                        }
                    }
                }
            }
        }
        return true;
    }

    public RBTreeNode<T> remove(T value) {
        return null;
    }

    public static void main(String[] args) {
        RBTree<Integer> tree = new RBTree<>();
        for (int i = 11; i >= 1; i--) {
            tree.add(i);
        }
        PrintBianryTree.printTree(tree.root);
    }
}
