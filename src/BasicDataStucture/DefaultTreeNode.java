package BasicDataStucture;

public class DefaultTreeNode<T> implements BinaryTreeNode<T> {
    T value;
    DefaultTreeNode<T> left;
    DefaultTreeNode<T> right;

    public DefaultTreeNode(T value, DefaultTreeNode<T> left, DefaultTreeNode<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public DefaultTreeNode(T value) {
        this(value, null, null);
    }

    @Override
    public T value() {
        return value;
    }

    @Override
    public BinaryTreeNode<T> left() {
        return left;
    }

    @Override
    public BinaryTreeNode<T> right() {
        return right;
    }

    @Override
    public void setLeft(BinaryTreeNode<T> node) {
        this.left = (DefaultTreeNode<T>) node;
    }

    @Override
    public void setRight(BinaryTreeNode<T> node) {
        this.right = (DefaultTreeNode<T>) node;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
