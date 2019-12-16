public class InsertSort {
    public static void sort(int[] arr, int num) {
        for (int i = 1; i < num; i++) {
            int j = i;
            int tmp = arr[i];
            while (j > 0 && tmp < arr[j - 1]) {
                arr[j] = arr[--j];
            }
            arr[j] = tmp;
        }
    }

    public static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.print("\n\r");
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 3, 4, 9, 8, 7};
        print(arr);
        sort(arr, 7);
        print(arr);
    }
}
