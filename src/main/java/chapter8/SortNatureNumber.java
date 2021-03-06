package chapter8;

import java.util.Scanner;
import java.util.Stack;

public class SortNatureNumber {
    public static void main(String[] args) {
        //test();

        Scanner in = new Scanner(System.in);
        Stack<String> s = new Stack<String>();
        while (!in.hasNext("efo")) {// in.hasNext()永远为真
            String str = in.nextLine();
            if (!str.equals("-"))
                s.push(str);
            else if (!s.isEmpty())
                System.out.println(s.pop() + " ");
        }
        // 这个语句永远无法执行
        System.out.println("(" + s.size() + "left on stack)");
    }


    public static void test() {
        Integer[] arr = {1, 2, 5, 3, 4};
        sort2(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void sort(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int curval = arr[i];
            while (curval != arr[curval - 1]) {
                int tmp = arr[curval - 1];
                arr[curval - 1] = curval;
                curval = tmp;
            }
        }
    }

    public static void sort1(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int tmp = arr[i];
            while (arr[i] != i + 1) {
                int next = arr[tmp - 1];
                arr[tmp - 1] = tmp;
                tmp = next;
            }
        }

    }

    public static void sort2(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int curval = arr[i];
            while (arr[i] != i + 1) {
                int tmp = arr[curval - 1];
                arr[curval - 1] = curval;
                arr[i] = tmp;
                curval = tmp;
            }
        }
    }
}
