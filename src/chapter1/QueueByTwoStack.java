package chapter1;

import java.util.Stack;

public class QueueByTwoStack {
    private Stack<Integer> stackPop;
    private Stack<Integer> stackPush;

    public QueueByTwoStack() {
        stackPop = new Stack<>();
        stackPush = new Stack<>();
    }

    private void pushToPop() {
        if (stackPop.empty()) {
            while (!stackPush.empty())
                stackPop.push(stackPush.pop());
        }
    }

    public Integer poll() {
        if (stackPop.empty() && stackPush.empty()) {
            throw new RuntimeException("Queue is empty");
        }
        pushToPop();
        return stackPop.pop();
    }

    public void push(Integer i) {
        stackPush.push(i);
        pushToPop();
    }

    public Integer peek() {
        if (stackPop.empty() && stackPush.empty()) {
            throw new RuntimeException("Queue is empty");
        }
        pushToPop();
        return stackPop.peek();
    }
}
