package leetcode;

import java.util.Stack;

public class ValidBrackets {
    /* 写这道题的时候, 我刚看了算法书, 但是没有实战.
     * 反映到这道题上, 就犯了很多错误.
     * 首先是没有考虑到 反括号 比正括号数量多的情况, 这样直接用 stack.peek 就会异常
     * 再次是低级的错误, 把 正括号 == 反括号当成 pop的依据.
     * 再次是认为正括号和反括号的int值相差一, 这只适用于(). [] , {} 不适用
     * 再次是没有考虑到正括号比反括号多的场景, 结尾要对stack的empty情况判断.
     */
    public static void main(String[] args) {
        String s = "()[]{}";
        Character c = 94;

        int i = '{';
        int j = '}';
        System.out.println(i);
        System.out.println(j);
        if (isValid(s)) {
            System.out.print(c);
        }

    }

    public static boolean isValid(String s) {
        if (s == null || s.isEmpty()) return true;
        Stack<Character> stack = new Stack<>();
        Character c = null;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            }
            if (c == '}' || c == ']' || c == ')') {
                if (!stack.empty() &&
                        ((c == '}' && stack.peek() == '{') ||
                                (c == ')' && stack.peek() == '(') ||
                                (c == ']' & stack.peek() == '['))) stack.pop();
                else {
                    return false;
                }
            }
        }
        return stack.empty();
    }
}
