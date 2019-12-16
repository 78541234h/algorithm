package chapter1;

import java.util.Stack;

// 第一个思路是对的, 逻辑也没问题, 只是不够精简, 第二个更好些

public class StackSort {
    public static void sortStack(Stack<Integer> stack) {
        Stack<Integer> tmp = new Stack<>();
        while (!stack.empty()) tmp.push(stack.pop());
        Integer cur;
        int count = 0;
        while (!tmp.empty()) {
            cur = tmp.pop();
            if (stack.empty() || stack.peek() <= cur)
                stack.push(cur);
            else {
                while (!stack.empty() && cur < stack.peek()) {
                    count++;
                    tmp.push(stack.pop());
                }
                for (; count != 0; count--)
                    stack.push(tmp.pop());
            }
        }
    }

    public static void better(Stack<Integer> stack) {
        Stack<Integer> help = new Stack<>();
        while (!stack.empty()) help.push(stack.pop());
        while (!help.empty()) {
            Integer cur = help.pop();
            while (!stack.empty() && stack.peek() > cur)
                help.push(stack.pop());
            stack.push(cur);
        }
    }
}
