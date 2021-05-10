
public class SegmentTree<E> {
    private E[] datas;
    private E[] tree;
    private Merger<E> merger;

    public interface Merger<E> {
        E merge(E left, E right);
    }

    @SuppressWarnings("unchecked")
    public SegmentTree(int size, Merger<E> merger) {
        datas = (E[]) new Object[size];
        tree = (E[]) new Object[size * 4];
        this.merger = merger;
    }

    public SegmentTree(E[] arr, Merger<E> merger) {
        this(arr.length, merger);
        System.arraycopy(arr, 0, datas, 0, arr.length);
        buildSegmentTree(0, 0, size() - 1);
    }

    public int size() {
        return datas.length;
    }

    public E get(int index) {
        if (index < 0 || index > size()) {
            throw new RuntimeException();
        }
        return datas[index];
    }

    private int leftChildIdx(int treeIdx) {
        return treeIdx * 2 + 1;
    }

    private int rightChildIdx(int treeIdx) {
        return treeIdx * 2 + 2;
    }

    private void buildSegmentTree(int treeIdx, int left, int right) {
        if (left == right) {
            tree[treeIdx] = merger.merge(datas[left], datas[right]);
            return;
        }
        int mid = (left + right) / 2;
        int leftChild = leftChildIdx(treeIdx);
        int rightChild = rightChildIdx(treeIdx);
        buildSegmentTree(leftChild, left, mid);
        buildSegmentTree(rightChild, mid + 1, right);
        tree[treeIdx] = merger.merge(tree[leftChild], tree[rightChild]);
    }

    public E query(int queryLeft, int queryRight) {
        if (queryLeft > queryRight || queryLeft < 0 || queryLeft > size() || queryRight > size())
            throw new RuntimeException();
        return query(0, 0, size() - 1, queryLeft, queryRight);
    }

    //在treeIdx为根的线段树中的【left，right】范围内，搜索区间【queryLeft，queryRight】的目标值。
    private E query(int treeIdx, int left, int right, int queryLeft, int queryRight) {
        if (left == queryLeft && right == queryRight)
            return tree[treeIdx];

        int mid = (left + right) / 2;
        int lc = leftChildIdx(treeIdx);
        int rc = rightChildIdx(treeIdx);

        //注意这里是大于号不是大于等于，因为mid被划分到左区间了！
        if (queryLeft > mid) {
            return query(rc, mid + 1, right, queryLeft, queryRight);
        } else if (queryRight <= mid) {
            return query(lc, left, mid, queryLeft, queryRight);
        }
        E res1 = query(lc, left, mid, queryLeft, mid);
        E res2 = query(rc, mid + 1, right, mid + 1, queryRight);
        return merger.merge(res1, res2);
    }

    public void update(int idx, E data) {
        if (idx < 0 || idx >= size())
            throw new RuntimeException();
        updateTree(0, 0, size() - 1, idx, data);
    }

    private void updateTree(int treeIdx, int left, int right, int idx, E e) {
        if (left == right) {
            tree[treeIdx] = e;
            return;
        }
        int mid = (left + right) / 2;
        int leftChild = leftChildIdx(treeIdx);
        int rightChild = rightChildIdx(treeIdx);
        if (idx <= mid) {
            updateTree(treeIdx * 2 + 1, left, mid, idx, e);
        } else {
            updateTree(treeIdx * 2 + 2, mid + 1, right, idx, e);
        }
        tree[treeIdx] = merger.merge(tree[leftChild], tree[rightChild]);
    }

}
