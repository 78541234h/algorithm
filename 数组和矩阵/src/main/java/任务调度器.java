import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class 任务调度器 {
    public static void main(String[] args) {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        System.out.println(leastInterval(tasks, 2));
    }


    public static char[] leastInterval(char[] tasks, int n) {
        if (tasks == null) return null;
        if (tasks.length < 2 || n == 0) return tasks;
        Arrays.sort(tasks);
        return null;
    }
}
