public class 找到无序数组中最小的K个数 {
    public static void main(String[] args) {

    }


    public static int[] getMinKNumsByHeap(int[] arr, int k) {
        if (k < 1 || k > arr.length) return arr;

        int[] heap = new int[k];
        for (int i = 0; i < k; i++) {
            heap[i] = arr[i];
        }
        buildHeap(heap);
        for (int i = k; i < arr.length; i++) {
            if (heap[0] > arr[i]) {
                heap[0] = arr[i];
                shiftDown(heap, 0);
            }
        }
        return heap;
    }

    private static void buildHeap(int[] heap) {
        for (int i = (heap.length - 1) / 2; i >= 0; i--) {
            shiftDown(heap, i);
        }
    }

    private static void shiftDown(int[] heap, int pos) {
        int cur = pos;
        int child;
        while ((child = cur * 2 + 1) < heap.length) {
            if (child < heap.length - 1 && heap[child + 1] > heap[child])
                child++;
            if (heap[child] > heap[cur]) {
                swap(heap, child, cur);
                cur = child;
            } else
                break;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
