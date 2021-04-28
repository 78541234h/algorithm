package learn.algorithm.dynamic;

public class 表达式得到期望结果的组成种数 {
    public static void main(String[] args) {

    }

    public static int num(String express, boolean desired) {
        if(check(express))
            return process(express, 0, express.length() - 1, desired);
        return -1;
    }

    private static boolean check(String express) {
        for (int i = 0; i < express.length(); i++) {
            char c = express.charAt(i);
            if ((i) % 2 == 0 && c != '0' && c != '1')
                return false;
            if ((i) % 2 == 1 && c != '|' && c != '^' && c != '&')
                return false;

        }
        return true;
    }

    private static int process(String express, int start, int end, boolean desired) {
        if (start == end) {
            char c = express.charAt(start);
            if ((c == '1' && desired) || (c == '0' && !desired))
                return 1;
            return 0;
        }
        int num = 0;

        int leftTrue = 0;
        int leftFalse = 0;
        int rightTrue = 0;
        int rightFalse = 0;
        for (int i = start + 1; i <= end - 1; i += 2) {
            char op = express.charAt(i);
            if ((op == '&' && desired) || (op == '|' && !desired)) {
                if (op == '&') {
                    leftTrue = process(express, start, i - 1, true);
                    rightTrue = process(express, i + 1, end, true);
                    num += leftTrue * rightTrue;
                } else {
                    leftFalse = process(express, start, i - 1, false);
                    rightFalse = process(express, i + 1, end, false);
                    num += leftFalse * rightFalse;
                }

            } else {
                leftFalse = process(express, start, i - 1, false);
                leftTrue = process(express, start, i - 1, true);
                rightFalse = process(express, i + 1, end, false);
                rightTrue = process(express, i + 1, end, true);
                if (op == '^') {
                    if (desired) {
                        num += leftFalse * rightTrue + leftTrue * rightFalse;
                    } else {
                        num += leftFalse * rightFalse + leftTrue * rightTrue;
                    }
                } else if(op == '&') {
                    num += leftFalse * rightFalse + leftFalse * rightTrue + leftTrue * rightFalse;
                } else {
                    num += leftTrue * rightTrue + leftFalse * rightTrue + leftTrue * rightFalse;
                }
            }
        }
        return num;
    }
}
