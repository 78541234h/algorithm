public class SimpleHeap {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 9, 6, 8, 4, 7, 2};
        buildHeap(arr, 9);
        print(arr);
        sort(arr, 9);
        print(arr);
    }

    public static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.print("\n\r");
    }

    public static void buildHeap(int[] heap, int size) {
        for (int i = (size - 2) / 2; i >= 0; i--) {
            adjust(heap, i, size);
        }
    }

    public static void sort(int[] heap, int size) {
        while(size > 1) {
            int tmp = heap[0];
            heap[0] = heap[--size];
            heap[size] = tmp;
            adjust(heap, 0, size);
        }
    }

    public static void adjust(int[] heap, int start, int size) {
        int j = start * 2 + 1;
        int k = start;
        int tmp = heap[start];
        while (j < size) {
            if (j < size - 1 && heap[j] > heap[j + 1]) j++;
            if (tmp <= heap[j]) break;
            heap[k] = heap[j];
            k = j;
            j = j * 2 + 1;
        }
        heap[k] = tmp;
    }
}
