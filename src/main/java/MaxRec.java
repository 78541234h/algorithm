import java.util.Stack;

public class MaxRec {
    public static void main(String[] args) {

    }

    public static int maxRecSize(int[][] map) {
        if (map == null || map.length == 0){
            return 0;
        }
        int maxRect = 0;
        int[] hight = new int[map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                hight[j] = map[i][j] == 0 ? 0 : hight[j] + 1;
            }
            maxRect = Math.max(maxRect, maxRecFromBottom(hight));
        }
        return maxRect;

    }

    public static int maxRecFromBottom(int[] hight) {
        if (hight == null || hight.length == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        for (int j = 0; j < hight.length; j++) {
            while(!stack.empty()) {

            }
        }
        return 0;
    }
}
