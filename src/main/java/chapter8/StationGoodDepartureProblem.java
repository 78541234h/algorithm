package chapter8;

public class StationGoodDepartureProblem {
    public static void main(String[] args) {
        System.out.println(-1 % 5);
    }

    /* description 每个加油站是不是良好的出发点
     *
     */
    public static boolean[] isStationAGoodStartingPoint(int[] oil, int[] dis) {
        if (oil == null || dis == null || oil.length != dis.length || oil.length < 1)
            throw new RuntimeException("传入参数不规范");
        boolean[] res = new boolean[oil.length];
        if (oil.length == 1) {
            res[0] = true;
            return res;
        }
        int init = 0, start, end, rest = 0, need = 0;
        for (int i = 0; i < dis.length; i++) {
            dis[i] = oil[i] - dis[i];
            if (dis[i] >= 0)
                init = i;
        }
        if (dis[init] < 0) return res;
        start = init;
        end = nextStation(start, dis.length);
        // 循环要分清有几层, 外层做什么, 内层做什么, 不要把嵌套关系弄反了
        // 循环的本质就是枚举, 对当前枚举的元素进行判断, 操作, 然后切换到下一个枚举的元素,
        // 要弄清楚枚举的每一个元素是什么
        // 在本例中, 循环枚举的是<code>start<code>的位置的元素
        // 循环是在本轮前进行check, for() while() 循环是每一轮前都check, 而 do{}while() 循环首轮不check
        do {
            if (start != init && start == preStation(end, oil.length))
                break;
            if (dis[start] < need) {
                need -= dis[start];
            } else {
                rest += dis[start] - need;
                need = 0;
                while (rest >= 0 && end != start) {
                    rest += dis[end];
                    end = nextStation(end, oil.length);
                }
                // 找到第一个可以跑一圈的点了
                if (rest >= 0) {
                    res[start] = true;
                    findOneSuccessfulPoint(dis, preStation(start, oil.length), init, res);
                    break;
                }
            }
            start = preStation(start, oil.length);
        } while (start != init);
        return res;
    }

    private static void findOneSuccessfulPoint(int[] dis, int start, int end, boolean[] res) {
        int need = 0;
        while (start != end) {
            if (dis[start] >= need) {
                res[start] = true;
                need = 0;
            } else {
                need -= dis[start];
            }
            start = preStation(start, dis.length);
        }
    }

    private static int nextStation(int cur, int totalNum) {
        return (cur + 1) % totalNum;
    }

    private static int preStation(int cur, int totalNum) {
        return (cur - 1 + totalNum) % totalNum;
    }
}
