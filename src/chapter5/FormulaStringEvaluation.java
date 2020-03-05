package chapter5;

import utils.PrintUtil;

import java.util.Stack;

public class FormulaStringEvaluation {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        String f1 = "48*((70-65)-43)+8*1";
        String f2 = "(3+1)*4";
        String f3 = "3+(1*4)";
        PrintUtil.print(calculate(f1));
        PrintUtil.print(calculate(f2));
        PrintUtil.print(calculate(f3));
    }

    public static int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Character> ops = new Stack<>();
        Stack<Integer> vals = new Stack<>();
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
                if (i == s.length() - 1 || s.charAt(i + 1) < '0' || s.charAt(i + 1) > '9') {
                    vals.push(num);
                    num = 0;
                    divisionOrMutiplication(ops, vals);
                }
            } else {
                if (c == ')') {
                    subCalculate(ops, vals);
                    divisionOrMutiplication(ops, vals);
                }
                if (c == '+' || c == '-' || c == '(' || c == '*' || c == '/')
                    ops.push(c);
            }
        }
        subCalculate(ops, vals);
        return vals.pop();
    }

    private static void subCalculate(Stack<Character> ops, Stack<Integer> vals) {
        char op;
        int v1, v2;
        while (!ops.empty() && (op = ops.pop()) != '(') {
            v2 = vals.pop();
            v1 = vals.pop();
            if (op == '+')
                v1 += v2;
            if (op == '-')
                v1 -= v2;
            vals.push(v1);
        }
    }

    private static void divisionOrMutiplication(Stack<Character> ops, Stack<Integer> vals) {
        char op;
        while (!ops.isEmpty() && (ops.peek() == '*' || ops.peek() == '/')) {
            op = ops.pop();
            int v2 = vals.pop();
            int v1 = vals.pop();
            v1 = op == '*' ? v1 * v2 : v1 / v2;
            vals.push(v1);
        }
    }
}
