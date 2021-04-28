package learn.algorithm.dynamic;

public class 排成一条线的纸牌博弈问题 {
    public static void main(String[] args) {
        int[] cards = {1, 2, 100, 4};
        System.out.print(maxScore(cards));
    }

    public static int maxScore(int[] cards) {
        if(cards == null || cards.length == 0)
            return 0;
        int len = cards.length;
        int[] sums = new int[len + 1];
        int sum = 0;
        for (int i = 1; i <= len; i++) {
            sum += cards[i - 1];
            sums[i] = sum;
        }
        int first = process(cards, sums, 0, len - 1);
        int second = sums[len] - first;
        return Math.max(first, second);
    }

    //start end范围内进行博弈，先手的最大得分
    private static int process(int[] arr, int[] sums, int start, int end) {
        if (start == end)
            return arr[start];
        int sum = sums[end + 1] - sums[start];
        return sum - Math.min(process(arr, sums, start + 1, end), process(arr, sums, start, end - 1));
    }



}
