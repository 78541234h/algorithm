package BasicDataStucture;

import chapter3.PrintBianryTree;
import chapter3.TreeNode;
import com.sun.org.apache.regexp.internal.RE;

public class RBTree<T extends Comparable<T>> {
    public enum COLOR {
        BLACK, RED
    }

    private int blackHeight = 0;

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
        if(n.rightChild != null) {
            RBTreeNode<T> rc = (RBTreeNode<T>) n.rightChild;
            rc.parent = n.parent;
            if(n != root) {
                if(n.parent.leftChild == n) n.parent.leftChild = rc;
                else n.parent.rightChild = rc;
            } else {
                root = rc;
            }
            if(rc.leftChild != null) ((RBTreeNode<T>)rc.leftChild).parent = n;
            n.rightChild = rc.leftChild;
            n.parent = rc;
            rc.leftChild = n;
        }
    }

    private void rightRotate(RBTreeNode<T> n) {
        if(n.leftChild != null) {
            RBTreeNode<T> lc = (RBTreeNode<T>) n.leftChild;
            lc.parent = n.parent;
            if(n != root){
                if(n == n.parent.leftChild) n.parent.leftChild = lc;
                else n.parent.rightChild = lc;
            } else {
                root = lc;
            }
            if(lc.rightChild != null)((RBTreeNode<T>)lc.rightChild).parent = n;
            n.leftChild = lc.rightChild;
            n.parent = lc;
            lc.rightChild = n;
        }
    }

    private void superRotate(RBTreeNode<T> n, RBTreeNode<T> p, RBTreeNode<T> gp) {
        if(gp != null) {
            if (p.rightChild == n && gp.leftChild == n) {
                leftRotate(p);
                rightRotate(gp);
            } else {
                rightRotate(p);
                leftRotate(gp);
            }
        } else if(p != null) {
            if (p.leftChild == n && gp.leftChild == p) {
                rightRotate(gp);
            } else if (p.rightChild == n && gp.rightChild == p) {
                leftRotate(gp);
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
                    superRotate(n);
                    tmp = p.parent == n ? n : p;
                    tmp.color = COLOR.BLACK;
                    break;
                }
            }
        }
        return true;
    }

    private RBTreeNode<T> successor(RBTreeNode<T> n) {
        TreeNode<T> rc = n.rightChild;
        if(rc != null) while(rc.leftChild != null) rc = rc.leftChild;
        return (RBTreeNode<T>) rc;
    }

    private void removeExposedNode(RBTreeNode<T> n) {
        if(n != null) {
            if(n == root) {
                root = null;
            } else {
                RBTreeNode<T> c = (RBTreeNode<T>) (n.leftChild == null ? n.rightChild : n.leftChild);
                if(c!=null) {
                    c.parent = n.parent;
                }
                if(n == n.parent.leftChild) {
                    n.parent.leftChild = c;
                } else {
                    n.parent.rightChild = c;
                }
                n.parent = null;
                n.leftChild = null;
                n.rightChild = null;
            }
        }
    }



    public RBTreeNode<T> remove(T value) {
        RBTreeNode<T> n,tmp,s;
        n = root;
        while(n != null) {
            if(n.value == value || n.value.equals(value))
                break;
            tmp = (RBTreeNode<T>) (n.value.compareTo(value) > 0 ? n.leftChild : n.rightChild);
            n = tmp;
        }
        if(n == null)
            return null;
        else if(n == root) {
            return root = null;
        }
        else if(n.leftChild != null && n.rightChild != null) {
            s = successor(n);
            n.value = s.value;
            n = s;
        }
        if(n.color == COLOR.RED) {
            removeExposedNode(n);
        } else if(n.rightChild != null) {
            removeExposedNode(n);
            ((RBTreeNode<T>)n.rightChild).color = COLOR.BLACK;
        } else {
            tmp = n;
            RBTreeNode<T> sib;
            while(n.color != COLOR.BLACK && n != root) {
                if(n == n.parent.leftChild) {
                    sib = (RBTreeNode<T>) n.parent.rightChild;
                } else {
                    sib = (RBTreeNode<T>) n.parent.leftChild;
                }
            }
        }
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