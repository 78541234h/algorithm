import sun.nio.cs.ext.MacHebrew;

public class 完美洗牌问题 {

    public static void main(String[] args) {
//        System.out.println(circleStart(4, 3));
        int[] arr = {1, 2, 3, 4, 5, 6};
        shuffle2(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void shuffle1(int[] arr) {
        assert arr.length % 2 == 0;
        int N = arr.length / 2;
        int cover = -1;
        for (int i = 0; i < arr.length; i++) {
            if (circleStart(i, N) > cover) {
                int cur = i;
                int preVal = arr[i];
                int circleMin = i;
                while ((cur = getTargetPos(cur, N)) != i) {
                    circleMin = Math.min(circleMin, cur);
                    int curVal = arr[cur];
                    arr[cur] = preVal;
                    preVal = curVal;
                }
                arr[i] = preVal;
                cover = Math.max(cover, circleMin);
            }
        }
    }

    private static int getTargetPos(int i, int N) {
        return i <= N - 1 ? (i + 1) * 2 - 1 : (i - N) * 2;
    }
//    private static int getTargetPos(int i, int N) {
//        return i <= N - 1 ? i * 2 : (i + 1 - N) * 2 - 1;
//    }

    private static int circleStart(int i, int N) {
        int min = i;
        int cur = i;
        while ((cur = getTargetPos(cur, N)) != i) {
            min = Math.min(cur, min);
        }
        return min;
    }


    public static void shuffle2(int[] arr) {
        if (arr == null || arr.length < 2 || arr.length % 2 == 1) return;
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int l, int r) {
        while (l < r) {
            int curLen = r - l + 1;
            int maxMatchLen = 2;
            int startNum = 0;
            for (int i = 2; i <= curLen; i = (i + 1) * 3 - 1) {
                maxMatchLen = i;
                startNum++;
            }

            if (maxMatchLen < curLen) {
                rotate(arr, l + maxMatchLen / 2, l + (maxMatchLen + curLen) / 2 - 1,
                        (curLen - maxMatchLen) / 2);
            }
            cycles(arr, l, maxMatchLen, startNum);
            l += maxMatchLen;
        }
    }

    private static void cycles(int[] arr, int start, int len, int k) {
        int N = len / 2;
        for (int i = 0, cycleStart = 0; i < k; i++) {
            int preValue = arr[start + cycleStart];
            int cur = cycleStart;
            while ((cur = getTargetPos(cur, N)) != cycleStart) {
                int curVal = arr[start + cur];
                arr[start + cur] = preValue;
                preValue = curVal;
            }
            arr[start + cycleStart] = preValue;
            cycleStart = (cycleStart + 1) * 3 - 1;
        }
    }

    public static void rotate(int[] arr, int l, int r, int leftLen) {
        reverse(arr, l, l + leftLen - 1);
        reverse(arr, l + leftLen, r);
        reverse(arr, l, r);
    }

    public static void reverse(int[] arr, int l, int r) {
        int halfLen = (r - l + 1) / 2;
        for (int i = 0; i < halfLen; i++) {
            int tmp = arr[l + i];
            arr[l + i] = arr[r - i];
            arr[r - i] = tmp;
        }
    }
}
