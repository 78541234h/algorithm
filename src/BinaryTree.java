import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {
    public TreeNode root = null;

    private class TreeNode {
        public TreeNode left;
        public TreeNode right;
        public int value;

        public TreeNode(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.add(5);
        tree.add(1);
        tree.add(6);
        tree.add(0);
        tree.add(3);
        tree.add(2);
        tree.add(4);
        preOrderUnRecur(tree.root);
        inOrderUnRecur(tree.root);
        //postOrderUnRecur(tree.root);
        //morrisPre(tree.root);

        //inOrder(tree.root, null);
        printByLevel(tree.root);
        // printByZigZag(tree.root, true);
        System.out.print(getHeight(tree.root, 0));

    }

    public static void preOrderUnRecur(TreeNode head) {
        Stack<TreeNode> stack = new Stack<>();
        if (head != null) {
            stack.push(head);
            while (!stack.empty()) {
                head = stack.pop();
                System.out.print(head.value + " ");
                if (head.right != null)
                    stack.push(head.right);
                if (head.left != null)
                    stack.push(head.left);
            }
        }
        System.out.println();
    }

    public static void inOrderUnRecur(TreeNode head) {
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.empty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                System.out.print(head.value + " ");
                head = head.right;
            }
        }
        System.out.println();
    }

    public static void postOrderUnRecur(TreeNode head) {
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        if (head != null) {
            s1.push(head);
            while (!s1.empty()) {
                head = s1.pop();
                s2.push(head);
                if (head.left != null)
                    s1.push(head.left);
                if (head.right != null)
                    s1.push(head.right);
            }
            while (!s2.empty()) {
                head = s2.pop();
                System.out.print(head.value + " ");
            }
        }
        System.out.println();
    }

    public void add(int value) {
        if (root == null) {
            root = new TreeNode(value);
            return;
        }
        TreeNode head = root;
        while (true) {
            if (head.value == value)
                return;
            else if (head.value > value) {
                if (head.left == null)
                    head.left = new TreeNode(value);
                else
                    head = head.left;
            } else {
                if (head.right == null)
                    head.right = new TreeNode(value);
                else
                    head = head.right;
            }
        }
    }

    public static void morrisPre(TreeNode head) {
        if (head != null) {
            TreeNode cur = head;
            TreeNode mostRight = null;
            while (cur != null) {
                mostRight = cur.left;
                if (mostRight != null) {
                    while (mostRight.right != null && mostRight.right != cur) {
                        mostRight = mostRight.right;
                    }
                    if (mostRight.right == null) {
                        mostRight.right = cur;
                        System.out.print(cur.value + " ");
                        cur = cur.left;
                        continue;
                    } else {
                        mostRight.right = null;
                    }
                } else {
                    System.out.print(cur.value + " ");
                }
                cur = cur.right;
            }
        }
    }

    public static TreeNode inOrder(TreeNode head, TreeNode pre) { //遍历每个节点时获取其上一个节点
        if (head == null)
            return pre;
        pre = inOrder(head.left, pre);
        if (pre == null)
            System.out.print("none ");
        else {
            System.out.print(pre.value + " ");
        }
        System.out.print(head.value + " ");
        return inOrder(head.right, head);
    }

    public static void printByLevel(TreeNode head) {
        if (head == null) {
            System.out.println("empty tree");
            return;
        }
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(head);
        int level = 1;
        System.out.print("level " + level++ + ": ");
        TreeNode last = head;
        TreeNode nLast = null;
        while (!que.isEmpty()) {
            head = que.poll();
            System.out.print(head.value + " ");
            if (head.left != null) {
                que.offer(head.left);
                nLast = head.left;
            }
            if (head.right != null) {
                que.offer(head.right);
                nLast = head.right;
            }
            if (head == last && !que.isEmpty()) {
                last = nLast;
                System.out.print("\n\rlevel " + level++ + ": ");
            }
        }
        System.out.println();
    }

    /*public static void printByZigZag(TreeNode head, boolean leftToRight) {
        if (head == null) {
            System.out.println("empty tree");
            return;
        }
        LinkedList<TreeNode> que = new LinkedList<>();
        que.offerFirst(head);
        if (leftToRight) {
            System.out.print("left to right: ");
        } else {
            System.out.print("right to left: ");
        }
        TreeNode first = head;
        TreeNode nfirst = null;
        while (!que.isEmpty()) {
            if (leftToRight) {
                head = que.pollFirst();
                if (head.left != null) {
                    que.offerLast(head.left);
                    nfirst = head.left;
                }
                if (head.right != null) {
                    que.offerLast(head.right);
                    nfirst = head.right;
                }
                if (head == first) {
                    System.out.print("left to right: ");
                }

            } else {
                head = que.pollLast();
                if (head.right != null) {
                    que.offerFirst(head.right);
                    nfirst = head.right;
                }
                if (head.left != null) {
                    que.offerFirst(head.left);
                    nfirst = head.left;
                }
                if (head == first) {
                    System.out.print("right to left: ");
                }
            }
            System.out.print(head.value + " ");
            if (head == last && !que.isEmpty()) {
                //last = nLast;
                if (leftToRight) {
                    System.out.print("\n\rright to left: ");
                } else {
                    System.out.print("\n\rleft to right: ");
                }
                leftToRight = !leftToRight;
            }
        }
        System.out.println();
    }*/


    public static int getHeight(TreeNode root, int height) {
        if (root == null) return height;
        return Math.max(getHeight(root.left, height + 1),
                getHeight(root.right, height + 1));
    }

    public TreeNode fromPostArrGenST(int[] arr, int begin, int end) {
        if (begin > end) return null;
        int lastSmall = -1;
        for (int i = begin; i < end; i++) {
            if (arr[i] < arr[end]) {
                lastSmall = i;
            }
        }
        TreeNode node = new TreeNode(arr[end]);
        node.left = lastSmall == -1 ? null : fromPostArrGenST(arr, begin, lastSmall);
        node.right = lastSmall == -1 ? fromPostArrGenST(arr, begin, end - 1) : fromPostArrGenST(arr, lastSmall + 1, end - 1);
        return node;
    }
}