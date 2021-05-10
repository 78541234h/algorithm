package learn.algorithm.binarytree;

import learn.algorithm.binarytree.common.TreeNode;

import java.util.Stack;


public class 判断t1树是否有与t2树拓扑结构完全相同的子树 {
    public static void main(String[] args) {
        TreeNode<Integer> t1 = TreeNode.mock3();
        TreeNode<Integer> t2 = TreeNode.mock4();
        System.out.print(hasSubTreeTopologyEqual(t1, t2));
    }

    public static boolean hasSubTreeTopologyEqual1(TreeNode<Integer> t1, TreeNode<Integer> t2) {
        if (t2 == null) return true;
        if (t1 == null) return false;
        return check(t1, t2) || check(t1.left, t2) || check(t1.right, t2);
    }

    public static boolean check(TreeNode<Integer> t1, TreeNode<Integer> t2) {
        if (t1 == null && t2 == null) return true;
        else if (t1 != null && t2 != null) {
            return t1.val.equals(t2.val) && check(t1.left, t2.left) && check(t1.right, t2.right);
        }
        return false;
    }


    public static boolean hasSubTreeTopologyEqual(TreeNode<Integer> t1, TreeNode<Integer> t2) {
        if (t2 == null) return true;
        if (t1 == null) return false;
        String s1 = serialize(t1);
        String s2 = serialize(t2);
        return s1.contains(s2);
    }

    public static String serialize(TreeNode<Integer> t) {
        if (t == null) return "";
        StringBuilder builder = new StringBuilder();
        Stack<TreeNode<Integer>> stack = new Stack<>();
        TreeNode<Integer> cur = t;
        stack.push(cur);
        TreeNode<Integer> nil = new TreeNode(0);
        while (!stack.empty()) {
            cur = stack.pop();
            if (cur == nil)
                builder.append("#!");
            else {
                builder.append(cur.val);
                builder.append("!");
                stack.push(cur.right == null ? nil : cur.right);
                stack.push(cur.left == null ? nil : cur.left);
            }
        }
        System.out.println(builder.toString());
        return builder.toString();
    }
}
