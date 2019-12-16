public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 9, 6, 8, 4, 7, 2};
        sort(arr, 0, 9);
        print(arr);
    }

    public static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.print("\n\r");
    }


    public static void sort(int[] arr, int begin, int end) {
        if (begin + 1 < end) {
            int left = begin;
            int right = end;
            while (left < right) {
                while (++left < end && arr[left] <= arr[begin]) ;
                while (--right > begin && arr[right] >= arr[begin]) ;
                if (left < right) {
                    swap(arr, left, right);
                }
            }
            swap(arr, begin, right);
            sort(arr, begin, right);
            sort(arr, left, end);
        }
    }

    public static void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }
}
