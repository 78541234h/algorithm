package chapter3;

import BasicDataStucture.BinaryTreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MaxBSTreeTopo {
    public static int maxBSTTopoSize1(BinaryTreeNode<Integer> head) {
        if (head == null) return 0;
        int size = maxTopo(head, head);
        int lsize = maxBSTTopoSize1(head.left());
        int rsize = maxBSTTopoSize1(head.right());
        return Math.max(size, Math.max(lsize, rsize));
    }

    private static int maxTopo(BinaryTreeNode<Integer> head, BinaryTreeNode<Integer> n) {
        if (isBSTNode(head, n)) {
            return maxTopo(head, n.left()) + maxTopo(head, n.right()) + 1;
        }
        return 0;
    }

    private static boolean isBSTNode(BinaryTreeNode<Integer> head, BinaryTreeNode<Integer> target) {
        BinaryTreeNode<Integer> cur = head;
        while (cur != null) {
            if (cur == target)
                return true;
            else if (cur.value().compareTo(target.value()) < 0) {
                cur = cur.right();
            } else {
                cur = cur.left();
            }
        }
        return false;
    }

    private static class Record {
        int lsize;
        int rsize;
    }

    public static int maxBSTTopoSize2(BinaryTreeNode<Integer> head) {
        if (head == null) return 0;
        Map<BinaryTreeNode<Integer>, Record> recordMap = new HashMap<>();
        return maxBSTTopoSize2(head, recordMap);
    }

    private static int maxBSTTopoSize2(BinaryTreeNode<Integer> head, Map<BinaryTreeNode<Integer>, Record> map) {
        if (head == null) return 0;
        int lsize = maxBSTTopoSize2(head.left(), map);
        int rsize = maxBSTTopoSize2(head.right(), map);
        modify(head, map, false);
        modify(head, map, true);
        Record lrecord = map.get(head.left());
        Record rrecord = map.get(head.right());
        Record record = new Record();
        if (lrecord != null) record.lsize = lrecord.lsize + lrecord.rsize + 1;
        if (rrecord != null) record.rsize = rrecord.lsize + rrecord.rsize + 1;
        map.put(head, record);
        return Math.max(record.lsize + record.rsize + 1, Math.max(lsize, rsize));
    }

    private static void modify(BinaryTreeNode<Integer> head, Map<BinaryTreeNode<Integer>, Record> map, boolean leftOrRight) {
        if ((leftOrRight && head.left() == null) || (!leftOrRight && head.right() == null)) return;
        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
        BinaryTreeNode<Integer> cur = leftOrRight ? head.left() : head.right();
        while (cur != null && map.containsKey(cur)) {
            if (leftOrRight && cur.value() > head.value()) break;
            if (!leftOrRight && cur.value() < head.value()) break;
            stack.push(cur);
            if (leftOrRight) cur = cur.right();
            else cur = cur.left();
        }
        if (cur != null && map.containsKey(cur)) {
            Record record = map.get(cur);
            int size = record.lsize + record.rsize + 1;
            map.remove(cur);
            while (!stack.empty()) {
                cur = stack.pop();
                if (leftOrRight) {
                    map.get(cur).rsize -= size;
                } else {
                    map.get(cur).lsize -= size;
                }
            }
        }
    }
}
