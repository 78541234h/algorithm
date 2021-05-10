import java.util.Deque;
import java.util.LinkedList;

public class 最大值减最小值等于num的子数组数量 {
    public static void main(String[] args) {
        Integer[] arr = RandomUtil.randomIntArray(0, 100, 100);
        PrintUtil.print(getSubArrayNum1(arr, 10));
        PrintUtil.newLine();
        PrintUtil.print(numOfSubArray(arr, 10));
    }


    public static int numOfSubArray(Integer[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 0) {
            return 0;
        }
        LinkedList<Integer> qmax = new LinkedList<>();
        LinkedList<Integer> qmin = new LinkedList<>();
        int left = 0;
        int right = 0;
        int res = 0;
        while (right < arr.length) {
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[right]) {
                qmax.removeLast();
            }
            while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[right]) {
                qmin.removeLast();
            }
            qmax.addLast(right);
            qmin.addLast(right);

            while (arr[qmin.peekFirst()] + num < arr[qmax.peekFirst()]) {
                if (qmax.peekFirst() <= left)
                    qmax.removeFirst();
                if (qmin.peekFirst() <= left)
                    qmin.removeFirst();
                left++;
            }
            res += right - left + 1;
            right++;
        }
        return res;
    }


    public static int getSubArrayNum1(Integer[] arr, int diff) {
        LinkedList<Integer> qmin = new LinkedList<>();
        LinkedList<Integer> qmax = new LinkedList<>();
        int head = 0, tail = 0;
        int num = 0;
        while (head < arr.length) {
            while (tail < arr.length) {
                if (qmin.isEmpty() || qmin.peekLast() != tail) {
                    while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[tail])
                        qmax.pollLast();
                    qmax.offerLast(tail);
                    while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[tail])
                        qmin.pollLast();
                    qmin.offerLast(tail);
                }
                if (arr[qmax.peek()] - arr[qmin.peek()] > diff)
                    break;
                tail++;
            }
            num += tail - head;
            if (qmin.peekFirst() == head)
                qmin.pollFirst();
            if (qmax.peekFirst() == head)
                qmax.pollFirst();
            head++;
        }
        return num;
    }
}
