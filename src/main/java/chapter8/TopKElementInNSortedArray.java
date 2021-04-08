package chapter8;

import utils.PrintUtil;

public class TopKElementInNSortedArray {
    public static void main(String[] args) {
        Integer[][] matrix = {{219, 405, 538, 845, 971}, {148, 558}, {52, 99, 348, 691}};
        printTopK(matrix, 5);
    }

    public static class HeapNode<T> {
        T value;
        int arrIndex; //from which array
        int index; //index in an array

        public HeapNode(T value, int arrIndex, int index) {
            this.value = value;
            this.arrIndex = arrIndex;
            this.index = index;
        }
    }

    public static <T extends Comparable<T>> void printTopK(T[][] matrix, int K) {
        HeapNode<T>[] heap = new HeapNode[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int index = matrix[i].length - 1;
            heap[i] = new HeapNode<T>(matrix[i][index], i, index);
            shiftUp(heap, i); //刚开始这写成　shiftUp(heap, index), 结果报exception
        }
        for (int i = 0, heapSize = heap.length; i < K && heapSize > 0; i++) {
            PrintUtil.print(heap[0].value);
            if (heap[0].index != 0) {
                heap[0].value = matrix[heap[0].arrIndex][--heap[0].index];
            } else {
                heap[0] = heap[--heapSize];
            }
            shiftDown(heap, 0, heapSize);
        }
    }


    private static <T extends Comparable<T>> void shiftUp(HeapNode<T>[] heap, int index) {
        HeapNode<T> node = heap[index];
        while (index != 0) {
            int parent = (index - 1) / 2;
            if (heap[parent].value.compareTo(node.value) < 0) {
                heap[index] = heap[parent];
                index = parent;
            } else break;
        }
        heap[index] = node;
    }

    private static <T extends Comparable<T>> void shiftDown(HeapNode<T>[] heap, int index, int size) {
        int left;
        HeapNode<T> node = heap[index];
        while ((left = index * 2 + 1) < size) {
            if (left < size - 1 && heap[left].value.compareTo(heap[left + 1].value) < 0) {
                left = left + 1;
            }
            if (heap[left].value.compareTo(node.value) > 0) {
                heap[index] = heap[left];
                index = left;
            } else break;
        }
        heap[index] = node;
    }
}

