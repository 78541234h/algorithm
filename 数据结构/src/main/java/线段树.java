import java.util.Map;

public class 线段树 {
    public static void main(String[] args) {
        Integer[] arr = {34, 65, 8, 10, 21, 86, 79, 30};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(arr, Math::max);
        System.out.print(segmentTree.query(1, 5));
    }
}
