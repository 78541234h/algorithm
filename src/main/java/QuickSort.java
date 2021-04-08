import utils.PrintUtil;

public class QuickSort {
    public static void main(String[] args) {
        testSort();
    }

    public static void testSort() {
        int[] arr = {1, 3, 5, 9, 6, 8, 4, 7, 2};
        sort(arr, 0, 8);
        print(arr);
    }

    public static void testPartition() {
        int[] arr = {5, 1, 3, 9, 6, 8, 4, 7, 2};
        PrintUtil.print(partition(arr, 0, 8, 5));
        PrintUtil.newLine();
        print(arr);
    }


    public static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.print("\n\r");
    }


    public static void sort(int[] arr, int begin, int end) {
        if (begin < end) {
            int small = begin;
            int big = end + 1;
            int pivot = arr[begin];
            while (small < big) {
                while (++small < end && arr[small] <= pivot) ;
                while (--big > begin && arr[big] >= pivot) ;
                if (small < big) {
                    swap(arr, small, big);
                }
            }
            swap(arr, begin, big);
            sort(arr, begin, big - 1);
            sort(arr, small, end);
        }
    }

    public static int[] partition(int[] arr, int begin, int end, int pivot) {
        int[] res = new int[2];
        int small = begin - 1;
        int big = end + 1;
        int cur = begin;
        while (cur < big) {
            if (arr[cur] == pivot)
                cur++;
            else if (arr[cur] < pivot) {
                swap(arr, ++small, cur++);
            } else {
                swap(arr, cur, --big);
            }
        }
        res[0] = small + 1;
        res[1] = big - 1;
        return res;
    }


    public static void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }
}
