package BasicDataStucture;


public class ArrayHeap<T extends Comparable<T>> {
    private Object[] array;
    private int size;
    private int capacity;
    private boolean minTop = true;

    @SuppressWarnings("unchecked")
    public ArrayHeap(int capacity, boolean minTop) {
        this.capacity = capacity;
        array = new Object[capacity];
        this.minTop = minTop;
    }

    public ArrayHeap(T[] array, boolean minTop) {
        this.minTop = minTop;
        this.array = array;
    }

    public ArrayHeap(int capacity) {
        this(capacity, true);
    }

    public void put(T value) {
        if (size >= capacity)
            throw new RuntimeException("outbound index");
        array[size++] = value;
        shiftUp(size - 1);
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private T valueAt(int index) {
        return (T) array[index];
    }

    private void shiftUp(int index) {
        shiftUp((T[]) array, index, minTop);
    }

    private static <T extends Comparable<T>> void shiftUp(T[] arr, int index, boolean minTop) {
        T tmp = arr[index];
        while (index > 0) {
            int p = (index - 1) / 2;
            if (arr[p].compareTo(tmp) < 0 ^ minTop) {
                arr[index] = arr[p];
                index = p;
            } else {
                break;
            }
        }
        arr[index] = tmp;
    }

    public void shiftDown(int index) {
        shiftDown((T[]) array, index, size - 1, minTop);
    }

    private static <T extends Comparable<T>> void shiftDown(T[] arr, int index, int lowerBound, boolean minTop) {
        T tmp = arr[index];
        int c;
        while ((c = index * 2 + 1) <= lowerBound) {
            if (c < lowerBound - 1 && ((arr[c].compareTo(arr[c + 1]) < 0) ^ minTop))
                c++;
            if (arr[c].compareTo(tmp) > 0 ^ minTop) index = c;
            else break;
        }
        arr[index] = tmp;
    }


    @SuppressWarnings("unchecked")
    public T peek() {
        return size == 0 ? null : (T) array[0];
    }

    @SuppressWarnings("unchecked")
    public T removePeek() {
        if (size == 0)
            throw new RuntimeException("empty heap");
        T tmp = (T) array[0];
        array[0] = array[--size];
        shiftDown(0);
        return tmp;
    }

    public static <T extends Comparable<T>> void sort(T[] array, boolean minFirst) {
        if (array != null) {
            for (int i = ((array.length - 2) / 2); i >= 0; i--) {
                shiftDown(array, i, array.length - 1, !minFirst);
            }
            for (int i = array.length - 1; i > 0; i--) {
                swap(array, 0, i);
                shiftDown(array, 0, i - 1, !minFirst);
            }
        }

    }

    private static <T> void swap(T[] arr, int a, int b) {
        T tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    public static void main(String[] args) {

    }

}
