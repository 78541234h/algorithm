package chapter1;

import java.util.Stack;

public class ReverseStack {
    public static void reverse(Stack<Integer> stack) {
        if(stack.empty())
            return;
        Integer i = getAndRemoveBottomElement(stack);
        reverse(stack);
        stack.push(i);
    }

    public static Integer getAndRemoveBottomElement(Stack<Integer> stack) {
        int element = stack.pop();
        if (stack.empty())
            return element;
        else {
            Integer result = getAndRemoveBottomElement(stack);
            stack.push(element);
            return result;
        }
    }
}
