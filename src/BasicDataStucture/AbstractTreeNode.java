package BasicDataStucture;

import sun.applet.AppletResourceLoader;

abstract public class AbstractTreeNode<C extends AbstractTreeNode<C, V>, V extends Comparable<V>> implements BinaryTreeNode<V> {
    protected V value;
    protected C left;
    protected C right;

    public AbstractTreeNode(V value, C left, C right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public V value() {
        return value;
    }

    @Override
    public void setLeft(BinaryTreeNode<V> node) {
        left = (C) node;
    }

    @Override
    public void setRight(BinaryTreeNode<V> node) {
        right = (C) node;
    }

    @Override
    public BinaryTreeNode<V> left() {
        return left;
    }

    @Override
    public BinaryTreeNode<V> right() {
        return right;
    }

}
