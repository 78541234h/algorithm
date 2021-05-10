import java.util.Random;
import java.util.Stack;

public class 可见山峰对的数量 {

    public static void main(String[] args) {
        int[] arr = {3, 5, 4, 4, 2, 4, 5, 3, 4, 5, 2};
        System.out.println(numRepeat(arr));
    }

    public static int numNonRepeat(int[] arr) {
        if (arr == null || arr.length < 2)
            return 0;
        return 2 * arr.length - 3;
    }

    public static class Record {
        int value;
        int times;

        public Record(int value, int times) {
            this.value = value;
            this.times = times;
        }

        public Record(int value) {
            this.value = value;
            this.times = 1;
        }
    }

    public static int numRepeat(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        if (arr.length == 2) return 1;
        Stack<Record> stack = new Stack<>();
        int start = 0;
        int len = arr.length;
        int num = 0;

        for (int i = 0; i < len; i++) {
            if (arr[i] > arr[start]) {
                start = i;
            }
        }
        int cur = start;
        do {
            while (!stack.empty() && stack.peek().value < arr[cur]) {
                int times = stack.pop().times;
                num += (times * (times - 1)) / 2 + times * 2;
            }
            if (!stack.empty() && stack.peek().value == arr[cur]) {
                stack.peek().times++;
            } else {
                stack.push(new Record(arr[cur], 1));
            }
            cur = (cur + 1) % len;
        } while (cur != start);

        //清算阶段，这里错了两次，第一次错是漏了stack.size==2的情况，也就是说把次高和最高之间计数当成其他情况了
        //第二次错是在计算次高和最高之间，漏了最高之间的计数。。。
        while (stack.size() > 2) {
            int times = stack.pop().times;
            num += getInternalNum(times) + times * 2;
        }
        if (stack.size() == 2) {
            int times = stack.pop().times;
            num += getInternalNum(times);
            num += stack.peek().times == 1 ? times : times * 2;
            num += getInternalNum(stack.pop().times);
        }
        return num;
    }

    public static int getInternalNum(int times) {
        return (times * (times - 1)) / 2;
    }

}
