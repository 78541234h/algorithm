package chapter3;

public class Morris {
    public static void main(String[] args) {
        TreeNode<Integer> root = TreeUtil.mockTree1();
        PrintBianryTree.printTree(root);
        Morris m = new Morris();
        m.morris(root);
        System.out.print("\n");
        m.preOrderWalk(root);
    }

    private <T> TreeNode<T> predecessor(TreeNode<T> n) {
        TreeNode<T> pre = n.leftChild;
        if (pre != null) {
            while (pre.rightChild != null && pre.rightChild != n) pre = pre.rightChild;
        }
        return pre;
    }

    private <T> void morris(TreeNode<T> n) {
        TreeNode<T> pre = null;
        while (n != null) {
            ;
            while ((pre = predecessor(n)) != null && pre.rightChild != n) {
                pre.rightChild = n;
                n = n.leftChild;
            }
            visit(n);
            if (pre != null) pre.rightChild = null;
            n = n.rightChild;
        }
    }


    public <T> void visit(TreeNode<T> n) {
        System.out.print(n + " ");
    }

    public <T> void preOrderWalk(TreeNode<T> root) {
        TreeNode<T> head = root;
        TreeNode<T> pre = null;
        while (head != null) {
            pre = head.leftChild;
            if(pre != null) {
                while(pre.rightChild != null && pre.rightChild != head) {
                    pre = pre.rightChild;
                }
                if(pre.rightChild == null) {
                    visit(head);
                    pre.rightChild = head;
                    head = head.leftChild;
                    continue;
                } else {
                    pre.rightChild = null;
                }
            } else {
                visit(head);
            }
            head = head.rightChild;
        }
    }

    public <T> void midOrderWalk(TreeNode<T> root) {
        TreeNode<T> head = root;
        TreeNode<T> pre = null;
        while (head != null) {
            pre = head.leftChild;
            if (pre != null) {
                while (pre.rightChild != null && pre.rightChild != head) {
                    pre = pre.rightChild;
                }
                if (pre.rightChild == null) {
                    pre.rightChild = head;
                    head = head.leftChild;
                    continue;
                } else {
                    pre.rightChild = null;
                }
            }
            visit(head);
            head = head.rightChild;
        }
    }

    public <T> void postOrderWalk(TreeNode<T> root) {
        TreeNode<T> head = root;
        TreeNode<T> pre = null;
        while(head != null) {
            pre = head.leftChild;
            if(pre != null) {
                while(pre.rightChild != null & pre.rightChild != head) {
                    pre = pre.rightChild;
                }
                if(pre.rightChild == null) {
                    pre.rightChild = head;
                    head = head.leftChild;
                    continue;
                }else {

                }
            }
            head = head.rightChild;
        }
    }

    private <T> void reverseRightBoundary(TreeNode<T> root) {
        TreeNode<T> head = root;
        TreeNode<T> pre = null;
        while(head != null) {

        }
    }
}