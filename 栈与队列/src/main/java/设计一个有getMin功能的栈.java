import java.lang.reflect.ParameterizedType;
import java.util.Stack;

public class 设计一个有getMin功能的栈 {
    public class SpecialStack {
        Stack<Integer> stack;
        Stack<Integer> mins;

        public SpecialStack() {
            stack = new Stack<>();
            mins = new Stack<>();
        }

        public void push(int i) {
            stack.push(i);
            //第一次写这里分神写错了，在empty分支中写了else分支的语句
            if (mins.empty()) {
                mins.push(i);
            } else {
                mins.push(Math.min(i, mins.peek()));
            }
        }

        public int getMin() {
            if (stack.empty()) {
                throw new RuntimeException();
            }
            return mins.peek();
        }

        public int pop() {
            if (stack.empty()) {
                throw new RuntimeException();
            }
            mins.pop();
            return stack.pop();
        }
    }
}
