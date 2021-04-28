package learn.algorithm.dynamic;

public class 汉诺塔进阶问题 {
    public static void main(String[] args) {
        int[] arr = {3, 3, 2, 1};
        System.out.print(hanoiStatus(arr));
    }

    public static int hanoiStatusRecursive(int[] arr) {
        return process(arr, arr.length - 1, 1, 3, 2);
    }

    private static int process(int[] arr, int n, int from, int to, int by) {
        if (n == -1) {
            return 0;
        }
        if (arr[n] == from) {
            return process(arr, n - 1, from, by, to);
        } else if (arr[n] == to) {
            int rest = process(arr, n - 1, by, to, from);
            if (rest == -1)
                return -1;
            return rest + 1 << n;
        } else {
            return -1;
        }
    }

    public static int hanoiStatus(int[] arr) {
        int steps = 0;
        int from = 1;
        int by = 2;
        int to = 3;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == from) {
                int tmp = by;
                by = to;
                to = tmp;
            } else if (arr[i] == to) {
                steps += 1 << i;
                int tmp = from;
                from = by;
                by = tmp;
            } else {
                return -1;
            }
        }
        return steps;
    }






}
