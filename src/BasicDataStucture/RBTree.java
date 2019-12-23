package BasicDataStucture;

import chapter3.PrintBianryTree;
import chapter3.TreeNode;

public class RBTree<T extends Comparable<T>> {
    public enum COLOR {
        BLACK, RED, UNKNOWN
    }

    private int blackHeight = 0;

    static public class RBTreeNode<T extends Comparable<T>> extends TreeNode<T> {
        public COLOR color;
        public RBTreeNode<T> parent;

        public RBTreeNode<T> left() {
            return (RBTreeNode<T>) super.leftChild;
        }

        public RBTreeNode<T> right() {
            return (RBTreeNode<T>) super.rightChild;
        }


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
        if (n.rightChild != null) {
            RBTreeNode<T> rc = n.right();
            rc.parent = n.parent;
            if (n != root) {
                if (n.parent.leftChild == n) n.parent.leftChild = rc;
                else n.parent.rightChild = rc;
            } else {
                root = rc;
            }
            if (rc.leftChild != null) rc.left().parent = n;
            n.rightChild = rc.leftChild;
            n.parent = rc;
            rc.leftChild = n;
        }
    }

    private void rightRotate(RBTreeNode<T> n) {
        if (n.leftChild != null) {
            RBTreeNode<T> lc = n.left();
            lc.parent = n.parent;
            if (n != root) {
                if (n == n.parent.leftChild) n.parent.leftChild = lc;
                else n.parent.rightChild = lc;
            } else {
                root = lc;
            }
            if (lc.rightChild != null) lc.right().parent = n;
            n.leftChild = lc.rightChild;
            n.parent = lc;
            lc.rightChild = n;
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
                tmp = p.value.compareTo(value) > 0 ? p.left() : p.right();
                if (tmp == null) break;
                p = tmp;
            }
            RBTreeNode<T> n = new RBTreeNode<>(value, COLOR.RED, p, null, null);
            if (p.value.compareTo(value) > 0) p.leftChild = n;
            else p.rightChild = n;
            while ((p = n.parent) != null && p.color == COLOR.RED) {
                gp = p.parent;
                u = gp.leftChild == p ? gp.right() : gp.left();
                if (u != null && u.color == COLOR.RED) {
                    // uncle is red, then uncle, parent, grandparent flip color
                    u.color = COLOR.BLACK;
                    p.color = COLOR.BLACK;
                    gp.color = gp == root ? COLOR.BLACK : COLOR.RED;
                    n = gp;             // continue loop
                } else {                // uncle is null or black then rotate <= 2 times, then exit
                    gp.color = COLOR.RED;
                    if (n == p.leftChild && p == gp.leftChild) {
                        p.color = COLOR.BLACK;
                        rightRotate(gp);
                    } else if (n == p.rightChild && p == gp.rightChild) {
                        p.color = COLOR.BLACK;
                        leftRotate(gp);
                    } else if (n == p.leftChild && p == gp.rightChild) {
                        n.color = COLOR.BLACK;
                        rightRotate(p);
                        leftRotate(gp);
                    } else {
                        n.color = COLOR.BLACK;
                        leftRotate(p);
                        rightRotate(gp);
                    }
                    break;
                }
            }
        }
        return true;
    }

    private RBTreeNode<T> successor(RBTreeNode<T> n) {
        RBTreeNode<T> rc = n.right();
        if (rc != null) while (rc.leftChild != null) rc = rc.left();
        return rc;
    }

    private RBTreeNode<T> removeLeaf(RBTreeNode<T> n) {
        if (n == root) {
            root = null;
        } else {
            if (n == n.parent.leftChild)
                n.parent.leftChild = null;
            else n.parent.rightChild = null;
            n.parent = null;
        }
        return n;
    }

    public RBTreeNode<T> remove(T value) {
        RBTreeNode<T> n, s, tmp;
        n = root;
        while (n != null) {
            if (n.value == value || n.value.equals(value))
                break;
            n = n.value.compareTo(value) > 0 ? n.left() : n.right();
        }
        if (n == null)
            return null;
        if (n.leftChild != null && n.rightChild != null) {
            s = successor(n);
            n.value = s.value;
            n = s;
        }
        //n has no more than one child
        if (n.color == COLOR.RED) { // the node will be removed is red, directly removed
            removeLeaf(n);
        } else {
            if (n.rightChild != null || n.leftChild != null) {
                // the node will be removed is black, and has one child then let its child replace it
                tmp = n.rightChild == null ? n.left() : n.right();
                if (n == n.parent.leftChild)
                    n.parent.leftChild = tmp;
                else n.parent.rightChild = tmp;
                tmp.parent = n.parent;
                tmp.color = COLOR.BLACK;
                n.rightChild = n.parent = null;
                if (n == root) root = tmp;
            } else {
                // the node will be removed is black without child
                tmp = n;
                RBTreeNode<T> sib;
                while (n.color == COLOR.BLACK && n != root) {
                    // n represent the node which is one black-height less than it should be
                    if (n == n.parent.leftChild) {
                        sib = n.parent.right();
                        if (sib.color == COLOR.RED) {
                            sib.color = COLOR.BLACK;
                            sib.parent.color = COLOR.RED;
                            leftRotate(sib.parent);
                            sib = n.parent.right();
                        }
                        if (colorOf(sib.leftChild) == COLOR.BLACK &&
                                colorOf(sib.rightChild) == COLOR.BLACK) {
                            sib.color = COLOR.RED;
                            n = n.parent; //continue
                        } else {
                            if (colorOf(sib.rightChild) == COLOR.BLACK) {
                                sib.color = COLOR.RED;
                                sib.left().color = COLOR.BLACK;
                                rightRotate(sib);
                                sib = sib.parent;
                            }
                            sib.color = sib.parent.color;
                            sib.parent.color = COLOR.BLACK;
                            sib.right().color = COLOR.BLACK;
                            leftRotate(sib.parent);
                            break;
                        }
                    } else {
                        sib = n.parent.left();
                        if (sib.color == COLOR.RED) {
                            sib.color = COLOR.BLACK;
                            sib.parent.color = COLOR.RED;
                            rightRotate(sib.parent);
                            sib = n.parent.left();
                        }
                        if (colorOf(sib.leftChild) == COLOR.BLACK && colorOf(sib.rightChild) == COLOR.BLACK) {
                            sib.color = COLOR.RED;
                            n = n.parent;
                        } else {
                            if (colorOf(sib.leftChild) == COLOR.BLACK) {
                                sib.color = COLOR.RED;
                                sib.left().color = COLOR.BLACK;
                                leftRotate(sib);
                                sib = sib.parent;
                            }
                            sib.color = sib.parent.color;
                            sib.parent.color = COLOR.BLACK;
                            sib.left().color = COLOR.BLACK;
                            rightRotate(sib.parent);
                            break;
                        }
                    }
                }
                n.color = COLOR.BLACK;
                n = removeLeaf(tmp);
            }
        }
        return n;
    }

    private void setColor(TreeNode<T> n, COLOR color) {
        if (n instanceof RBTreeNode) {
            ((RBTreeNode<T>) n).color = color;
        }
    }

    private RBTreeNode<T> sibOf(RBTreeNode<T> n) {
        return (RBTreeNode<T>) (n.parent == null ? null : n == n.parent.leftChild ? n.parent.rightChild : n.parent.rightChild);
    }

    private COLOR colorOf(TreeNode<T> n) {
        return n == null ? COLOR.BLACK : n instanceof RBTreeNode ? ((RBTreeNode<T>) n).color : COLOR.UNKNOWN;
    }

    public boolean check(RBTree<T> tree) {

        return false;
    }

    public static void main(String[] args) {
        RBTree<Integer> tree = new RBTree<>();
        for (int i = 20; i >= 1; i--) {
            tree.add(i);
        }
        PrintBianryTree.printTree(tree.root);
        tree.remove(10);
        System.out.println("======================================================");
        PrintBianryTree.printTree(tree.root);
    }
}