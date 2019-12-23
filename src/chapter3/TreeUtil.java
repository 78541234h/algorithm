package chapter3;

class TreeUtil {
    static <T extends Comparable<T>> void add(TreeNode<T> root, T value) {
        while (true) {
            if (root.value == value ||root.value.equals(value) )
                return;
            else if (root.value.compareTo(value) > 0) {
                if (root.leftChild == null)
                    root.leftChild = new TreeNode<T>(value, null, null);
                else
                    root = root.leftChild;
            } else {
                if (root.rightChild == null)
                    root.rightChild = new TreeNode<T>(value, null, null);
                else
                    root = root.rightChild;
            }
        }
    }

    static TreeNode<Integer> mockTree1() {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.leftChild = new TreeNode<>(200000);
        root.leftChild.rightChild = new TreeNode<>(4);
        root.leftChild.rightChild.leftChild = new TreeNode<>(7);
        root.leftChild.rightChild.rightChild = new TreeNode<>(8);
        root.leftChild.rightChild.rightChild.rightChild = new TreeNode<>(11);
        root.leftChild.rightChild.rightChild.rightChild.leftChild = new TreeNode<>(13);
        root.leftChild.rightChild.rightChild.rightChild.rightChild = new TreeNode<>(14);

        root.rightChild = new TreeNode<>(3);
        root.rightChild.leftChild = new TreeNode<>(5);
        root.rightChild.rightChild = new TreeNode<>(6);
        root.rightChild.leftChild.leftChild = new TreeNode<>(9);
        root.rightChild.leftChild.rightChild = new TreeNode<>(10);
        root.rightChild.leftChild.leftChild.leftChild = new TreeNode<>(12);
        root.rightChild.leftChild.leftChild.leftChild.leftChild = new TreeNode<>(15);
        root.rightChild.leftChild.leftChild.leftChild.rightChild = new TreeNode<>(16);

        return root;
    }
}
