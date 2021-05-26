import java.util.Deque;
import java.util.LinkedList;

public class 公式字符串求值 {
    public static void main(String[] args) {
        String str = "48*((70-65)-43)+8*1";
//        str = "3+(1*4)";
        str = " 2-1 + 2 ";
        str = "(1+(4+5+2)-3)+(6+8)";
        str = "1-(+1+1)";
        System.out.println(getExpressionResult(str));
    }

    public static double getExpressionResult(String exp) {
        if (exp == null || exp.length() == 0) return 0;
        if (exp.length() == 1) return Integer.parseInt(exp);
        exp = exp + "+0";

        Deque<Character> ops = new LinkedList<>();
        Deque<Double> nums = new LinkedList<>();
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (c >= '0' && c <= '9') {
                if (i > 0) {
                    char pre = exp.charAt(i - 1);
                    if (pre >= '0' && pre <= '9') {
                        nums.addLast(nums.removeLast() * 10 + c - '0');
                        continue;
                    }
                }
                nums.addLast((double) (c - '0'));
            } else {
                if (c == '(') {
                    ops.addLast(c);
                } else if (c == ')') {
                    if (ops.peekLast() == '*' || ops.peekLast() == '/') {
                        char op = ops.removeLast();
                        double b = nums.removeLast();
                        double a = nums.removeLast();
                        if (op == '*') {
                            nums.addLast(a * b);
                        } else {
                            nums.addLast(a / b);
                        }
                    }
                    if (ops.peekLast() != '(') {
                        Deque<Character> tmpOps = new LinkedList<>();
                        Deque<Double> tmpNums = new LinkedList<>();
                        tmpNums.addFirst(nums.removeLast());
                        while (ops.peekLast() != '(') {
                            tmpOps.addFirst(ops.removeLast());
                            tmpNums.addFirst(nums.removeLast());
                        }
                        nums.addLast(calculate(tmpOps, tmpNums));
                    }
                    ops.removeLast();
                } else if ((c == '-' || c == '+') &&
                        (i == 0 || exp.charAt(i - 1) == '(')) {
                    nums.addLast(.0);
                    ops.addLast(c);
                } else if (!ops.isEmpty()) {
                    char preOp = ops.peekLast();
                    if (preOp == '*' || preOp == '/') {
                        double b = nums.removeLast();
                        double a = nums.removeLast();
                        if (preOp == '*')
                            nums.addLast(a * b);
                        else {
                            nums.addLast(a / b);
                        }
                        ops.removeLast();
                    }
                    ops.addLast(c);
                } else {
                    ops.addLast(c);
                }
            }
        }
        return calculate(ops, nums);
    }

    private static Double calculate(Deque<Character> ops, Deque<Double> nums) {
        while (!ops.isEmpty()) {
            double a = nums.removeFirst();
            double b = nums.removeFirst();
            char op = ops.removeFirst();
            if (op == '+')
                nums.addFirst(a + b);
            else
                nums.addFirst(a - b);
        }
        return nums.pop();
    }
}
