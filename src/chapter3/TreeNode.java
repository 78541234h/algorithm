package chapter3;

public class TreeNode<T> {
    public T value;
    public TreeNode<T> leftChild;
    public TreeNode<T> rightChild;

    public TreeNode(T value) {
        this.value = value;
        leftChild = null;
        rightChild = null;
    }

    public TreeNode(T value, TreeNode<T> leftChild, TreeNode<T> rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
}