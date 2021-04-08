package chapter1;

import java.util.Stack;

// err1: from stack not check empty
// err2: action not set in process function

// this problem is the modified hanoi problem
// .
public class HanoiWithStack {
    public static void hanoiRecur(int n) {
        if (n < 1) return;
        int res = recurProc(n, "left", "mid", "right");
        System.out.println("It will move " + res + " steps");
    }

    private static int recurProc(int n, String a, String b, String c) {
        if (n == 1) {
            System.out.println("move 1 from " + a + " to " + b);
            System.out.println("move 1 from " + b + " to " + c);
            return 2;
        } else {
            int res = 0;
            res += recurProc(n - 1, a, b, c) * 3 + 2;
            System.out.println("move " + n + " from: " + a + " to " + b);
            recurProc(n - 1, c, b, a);
            System.out.println("move " + n + " from: " + b + " to " + c);
            recurProc(n - 1, a, b, c);
            return res;
        }
    }

    enum ACTION {
        NO_ACTION,
        LEFT_TO_MID, MID_TO_LEFT,
        MID_TO_RIGHT, RIGHT_TO_MID
    }

    public static void hanoiUnRecur(int n) {
        Stack<Integer> left = new Stack<>();
        Stack<Integer> right = new Stack<>();
        Stack<Integer> mid = new Stack<>();
        long steps = 0;
        for (int i = n; i > 0; i--)
            left.push(i);
        ACTION[] pre = {ACTION.NO_ACTION};
        while (right.size() != n) {
            steps += move(ACTION.MID_TO_LEFT, ACTION.LEFT_TO_MID, pre, left, mid, "left", "mid");
            steps += move(ACTION.LEFT_TO_MID, ACTION.MID_TO_LEFT, pre, mid, left, "mid", "left");
            steps += move(ACTION.RIGHT_TO_MID, ACTION.MID_TO_RIGHT, pre, mid, right, "mid", "right");
            steps += move(ACTION.MID_TO_RIGHT, ACTION.RIGHT_TO_MID, pre, right, mid, "right", "mid");
        }
        System.out.println("It will move " + steps + " steps");
    }

    private static int move(ACTION preForbid, ACTION cur, ACTION[] pre, Stack<Integer> fromStack, Stack<Integer> toStack, String from, String to) {
        if (pre[0] != preForbid && !fromStack.empty() && (toStack.empty() || toStack.peek() > fromStack.peek())) {
            System.out.println("Move " + fromStack.peek() + " from " + from + " to " + to);
            toStack.push(fromStack.pop());
            pre[0] = cur;
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        //hanoiRecur(1);
        hanoiUnRecur(2);
    }
}
