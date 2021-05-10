package learn.algorithm.binarytree;

import learn.algorithm.binarytree.common.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class 二叉数节点之间的最近公共祖先 {

    public static TreeNode lowestAncestor(TreeNode root, TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) return null;
        else if (n1 == null) return n2;
        else if (n2 == null) return n1;
        else if (n1 == n2) return n1;

        HashMap<TreeNode, TreeNode> map = new HashMap<>();
        buildMap(root, null, map);
        Set<TreeNode> ancestors = new HashSet<>();
        TreeNode cur = n1;
        while (cur != null) {
            ancestors.add(cur);
            cur = map.get(cur);
        }
        cur = n2;
        while (cur != null && !ancestors.contains(cur)) {
            cur = map.get(cur);
        }
        return cur;
    }


    private static void buildMap(TreeNode node, TreeNode parent, HashMap<TreeNode, TreeNode> map) {
        if (node == null) return;
        map.put(node, parent);
        buildMap(node.left, node, map);
        buildMap(node.right, node, map);
    }
}
