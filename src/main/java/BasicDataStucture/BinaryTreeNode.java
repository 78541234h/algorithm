package BasicDataStucture;

public interface BinaryTreeNode<T> {
    T value();

    BinaryTreeNode<T> left();

    BinaryTreeNode<T> right();

    void setLeft(BinaryTreeNode<T> node);

    void setRight(BinaryTreeNode<T> node);

    void setValue(T value);
}
