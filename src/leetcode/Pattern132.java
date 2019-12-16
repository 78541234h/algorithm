package leetcode;

import java.util.HashMap;
import java.util.Stack;

public class Pattern132 {
    public static void main(String[] args) {
        int[] arr = {4, 1, 3, 2};
        if (has132pattern(arr)) {
            System.out.print("adsfasfdasdfasdfasfs");
        }
    }

    public static boolean has132pattern(int[] arr) {
        if (arr != null && arr.length > 2) {
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < arr.length; i++) {
                if (stack.empty() || arr[i] > stack.peek())
                    stack.push(arr[i]);
                else {
                    if (arr[i] < stack.peek()) {
                        stack.pop();
                        if (!stack.empty() && arr[i] > stack.peek()) return true;
                    }
                    stack.clear();
                    stack.push(arr[i]);
                }
            }
        }
        return false;
    }

    public static boolean l456(int[] nums) {
        //如果数组长度小于三，返回false
        if (nums.length < 3) {
            return false;
        }
        //定义递增区间的头和尾
        int head = 0;
        int tail = 0;
        //当前得到的数组的最小值,最大值
        int min = nums[0];
        int max = nums[0];
        //是否是递增趋势
        boolean up = false;
        //定义一个储存区间的数据结构
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i < nums.length; i++) {


            if (nums[i] > nums[i - 1]) {
                //如果是后一个比前一个大，更新尾
                tail = i;
                if (!up) {
                    //更新头为第一个，确定当前是递增趋势（至少需要两个数才能判断是否为递增趋势）
                    head = i - 1;
                    //确定递增趋势，不再更新头。
                    up = true;
                }
            }
            if (nums[i] < nums[i - 1]) {
                //递增趋势结束
                up = false;
                //存放区间
                map.put(head, tail);
                //更新最小值和最大值
                min = Math.min(nums[head], min);
                max = Math.max(max, nums[tail]);
            }
            //判断是否应该进行与区间两端进行比较
            if (nums[i] > min && nums[i] < max) {
                for (Integer integer : map.keySet()) {
                    //判断132模式
                    if (nums[i] > nums[integer] && nums[i] < nums[map.get(integer)]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        int last = Integer.MIN_VALUE; // 132中的2
        Stack<Integer> sta = new Stack<>();// 用来存储132中的3
        if (nums.length < 3)
            return false;
        for (int i = n - 1; i >= 0; i--) {

            if (nums[i] < last) // 若出现132中的1则返回正确值
                return true;

            // 若当前值大于或等于2则更新2（2为栈中小于当前值的最大元素）
            while (!sta.isEmpty() && sta.peek() < nums[i]) {
                last = sta.pop();
            }

            // 将当前值压入栈中
            sta.push(nums[i]);
        }
        return false;
    }


    public boolean find132Pattern(int[] arr) {
        if (arr == null || arr.length <= 2) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        int s2 = Integer.MIN_VALUE;
        for (int i = arr.length - 1; i >= 0; i++) {
            if (arr[i] < s2) return true;
            while (!stack.empty() && arr[i] > stack.peek()) {
                s2 = stack.pop();
            }
            stack.push(arr[i]);
        }
        return false;
    }



}
