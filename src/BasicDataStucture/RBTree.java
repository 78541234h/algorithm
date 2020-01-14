package BasicDataStucture;

import chapter3.PrintBianryTree;

import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class RBTree<T extends Comparable<T>> {
    public enum COLOR {
        BLACK, RED, UNKNOWN
    }

    private int blackHeight = 0;

    static public class RBTreeNode<T extends Comparable<T>> extends AbstractTreeNode<RBTreeNode<T>, T> {
        public COLOR color;
        public RBTreeNode<T> parent;

        public RBTreeNode(T value, COLOR color, RBTreeNode<T> parent, RBTreeNode<T> left, RBTreeNode<T> right) {
            super(value, left, right);
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
        if (n.right != null) {
            RBTreeNode<T> rc = n.right;
            rc.parent = n.parent;
            if (n != root) {
                if (n.parent.left == n) n.parent.left = rc;
                else n.parent.right = rc;
            } else {
                root = rc;
            }
            if (rc.left != null) rc.left.parent = n;
            n.right = rc.left;
            n.parent = rc;
            rc.left = n;
        }
    }

    private void rightRotate(RBTreeNode<T> n) {
        if (n.left != null) {
            RBTreeNode<T> lc = n.left;
            lc.parent = n.parent;
            if (n != root) {
                if (n == n.parent.left) n.parent.left = lc;
                else n.parent.right = lc;
            } else {
                root = lc;
            }
            if (lc.right != null) lc.right.parent = n;
            n.left = lc.right;
            n.parent = lc;
            lc.right = n;
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
                tmp = p.value.compareTo(value) > 0 ? p.left : p.right;
                if (tmp == null) break;
                p = tmp;
            }
            RBTreeNode<T> n = new RBTreeNode<>(value, COLOR.RED, p, null, null);
            if (p.value.compareTo(value) > 0) p.left = n;
            else p.right = n;
            while ((p = n.parent) != null && p.color == COLOR.RED) {
                gp = p.parent;
                u = gp.left == p ? gp.right : gp.left;
                if (u != null && u.color == COLOR.RED) {
                    // uncle is red, then uncle, parent, grandparent flip color
                    u.color = COLOR.BLACK;
                    p.color = COLOR.BLACK;
                    gp.color = gp == root ? COLOR.BLACK : COLOR.RED;
                    n = gp;             // continue loop
                } else {                // uncle is null or black then rotate <= 2 times, then exit
                    gp.color = COLOR.RED;
                    if (n == p.left && p == gp.left) {
                        p.color = COLOR.BLACK;
                        rightRotate(gp);
                    } else if (n == p.right && p == gp.right) {
                        p.color = COLOR.BLACK;
                        leftRotate(gp);
                    } else if (n == p.left && p == gp.right) {
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
        RBTreeNode<T> rc = n.right;
        if (rc != null) while (rc.left != null) rc = rc.left;
        return rc;
    }

    private RBTreeNode<T> removeLeaf(RBTreeNode<T> n) {
        if (n == root) {
            root = null;
        } else {
            if (n == n.parent.left)
                n.parent.left = null;
            else n.parent.right = null;
            n.parent = null;
        }
        return n;
    }

    public RBTreeNode<T> remove(T value) {
        RBTreeNode<T> n, tmp;
        n = root;
        while (n != null) {
            if (n.value == value || n.value.equals(value))
                break;
            n = n.value.compareTo(value) > 0 ? n.left : n.right;
        }
        if (n == null)
            return null;
        if (n.left != null && n.right != null) {
            tmp = successor(n);
            n.value = tmp.value;
            n = tmp;
        }
        //n has no more than one child
        if (n.color == COLOR.BLACK) {
            tmp = n.right == null ? n.left : n.right;
            if (tmp != null) {
                // the node will be removed is black, and has one child then let its child replace it, then delete it child
                n.value = tmp.value;
            } else {
                // the node will be removed is black without child
                tmp = n;
                RBTreeNode<T> sib;
                while (n.color == COLOR.BLACK && n != root) {
                    // n represent the node which is one black-height less than it should be
                    if (n == n.parent.left) {
                        sib = n.parent.right;
                        if (sib.color == COLOR.RED) {
                            sib.color = COLOR.BLACK;
                            sib.parent.color = COLOR.RED;
                            leftRotate(sib.parent);
                            sib = n.parent.right;
                        }
                        if (colorOf(sib.left) == COLOR.BLACK &&
                                colorOf(sib.right) == COLOR.BLACK) {
                            sib.color = COLOR.RED;
                            n = n.parent; //continue
                        } else {
                            if (colorOf(sib.right) == COLOR.BLACK) {
                                sib.color = COLOR.RED;
                                sib.left.color = COLOR.BLACK;
                                rightRotate(sib);
                                sib = sib.parent;
                            }
                            sib.color = sib.parent.color;
                            sib.parent.color = COLOR.BLACK;
                            sib.right.color = COLOR.BLACK;
                            leftRotate(sib.parent);
                            break;
                        }
                    } else {
                        sib = n.parent.left;
                        if (sib.color == COLOR.RED) {
                            sib.color = COLOR.BLACK;
                            sib.parent.color = COLOR.RED;
                            rightRotate(sib.parent);
                            sib = n.parent.left;
                        }
                        if (colorOf(sib.left) == COLOR.BLACK && colorOf(sib.right) == COLOR.BLACK) {
                            sib.color = COLOR.RED;
                            n = n.parent;
                        } else {
                            if (colorOf(sib.left) == COLOR.BLACK) {
                                sib.color = COLOR.RED;
                                sib.left.color = COLOR.BLACK;
                                leftRotate(sib);
                                sib = sib.parent;
                            }
                            sib.color = sib.parent.color;
                            sib.parent.color = COLOR.BLACK;
                            sib.left.color = COLOR.BLACK;
                            rightRotate(sib.parent);
                            break;
                        }
                    }
                }
                n.color = COLOR.BLACK;
            }
            n = tmp;
        }
        return removeLeaf(n);
    }


    private RBTreeNode<T> sibOf(RBTreeNode<T> n) {
        return (RBTreeNode<T>) (n.parent == null ? null : n == n.parent.left ? n.parent.right : n.parent.right);
    }

    private COLOR colorOf(RBTreeNode<T> n) {
        return n == null ? COLOR.BLACK : n.color;
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