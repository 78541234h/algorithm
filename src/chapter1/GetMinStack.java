package chapter1;

import java.util.Stack;

// 有两种实现get min功能stack的方法,其中,均使用了两个stack, 一个正常存数, 一个存最小的值
public class GetMinStack {
    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();

    public void push(Integer i) {
        if (stack2.empty() || i < stack2.peek()) {
            stack2.push(i);
        } else {
            stack2.push(stack2.peek());
        }
        stack1.push(i);
    }

    public Integer getMin() {
        if (stack1.empty()) {
            throw new RuntimeException("your stack is empty");
        }
        return stack2.peek();
    }

    public Integer pop() {
        if (stack1.empty()) {
            throw new RuntimeException("your stack is empty");
        }
        stack1.pop();
        return stack2.pop();
    }

    public void push2(Integer i) {
        if (stack1.empty() || i <= stack2.peek()){
            stack2.push(i);
        }
        stack1.push(i);
    }

    public Integer pop2() {
        if(stack1.empty())
            throw new RuntimeException("your stack is empty");
        Integer i = stack1.pop();
        if(i.equals(stack2.peek()))
            stack2.pop();
        return i;
    }
}
