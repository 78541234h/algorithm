package learn.algorithm.dynamic;

public class 数字字符串转化为字母组合的种数 {
    public static void main(String[] args) {
        System.out.print(convertNum("1111"));
    }
    public static int convertNum(String str) {
        if (str.charAt(0) > '9' || str.charAt(0) < '1')
            return 0;
        int prepreNum = 1;
        int preNum = 1;
        int res = 0;
        for (int i = 1; i < str.length(); i++) {
            char c = str.charAt(i);
            char pc = str.charAt(i - 1);
            if (c < '0' || c > '9')
                return 0;
            if (c == '0') {
                if (pc < '1' || pc > '2')
                    return 0;
                res = prepreNum;
            } else {
                if (pc < '1' || pc > '2') {
                    res = preNum;
                } else {
                    int num = (pc - '0') * 10 + (c - '0');
                    if (num > 26)
                        res = preNum;
                    else
                        res = prepreNum + preNum;
                }
            }
            prepreNum = preNum;
            preNum = res;
        }
        return res;
    }

}
