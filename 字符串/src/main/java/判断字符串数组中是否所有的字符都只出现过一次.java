public class 判断字符串数组中是否所有的字符都只出现过一次 {
    public static void main(String[] args) {
        char[] chas = {'1', '4', '8', '3', '5', '2', '9', '6', '7'};
        System.out.print(hasRepeatedChar2(chas));
    }

    public static boolean hasRepeatedChar(char[] chas) {
        if (chas == null || chas.length < 2) return true;
        int[] map = new int[256];
        for (int i = 0; i < chas.length; i++) {
            if (map[chas[i]]++ > 0)
                return true;
        }
        return false;
    }

    public static boolean hasRepeatedChar2(char[] chas) {
        if (chas == null || chas.length < 2) return true;
        heapSort(chas);
        for (int i = 1; i < chas.length; i++) {
            if (chas[i] == chas[i - 1])
                return true;
        }
        return false;
    }

    public static void buildHeap(char[] chas) {
        int start = (chas.length - 2) / 2;
        for (int i = start; i >= 0; i--) {
            shiftDown(chas, i, chas.length - 1);
        }
    }

    public static void shiftDown(char[] heap, int pos, int end) {
        int par = pos;
        int child;
        //第一次写child = par * 2，导致算法出错。。。这种低级问题，以后不能犯！
        while ((child = par * 2 + 1) <= end) {
            if (child < end && heap[child] < heap[child + 1]) {
                child = child + 1;
            }
            if (heap[par] < heap[child]) {
                swap(heap, par, child);
                par = child;
            } else {
                break;
            }
        }
    }

    public static void swap(char[] chas, int i, int j) {
        char tmp = chas[i];
        chas[i] = chas[j];
        chas[j] = tmp;
    }


    public static void heapSort(char[] chas) {
        buildHeap(chas);
        for (int i = chas.length - 1; i > 0; i--) {
            swap(chas, 0, i);
            shiftDown(chas, 0, i - 1);
        }
    }
}
