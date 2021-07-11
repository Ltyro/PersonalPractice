package main.java.com.lnstark.pp.datastructure;

import java.util.function.Consumer;

/**
 * 红黑树
 * https://www.jianshu.com/p/83b9b75c9405
 */
public class RedBlackTree<T extends Comparable<T>> {

    private TreeNode<T> root;

    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        TreeNode<Integer> node = new TreeNode<>(3);
        node.left = new TreeNode<>(2);
        node.left.parent = node;
        node.left.right = new TreeNode<>(1);
        node.left.right.parent = node.left;
        node.left.right.left = new TreeNode<>(4);
        node.left.right.left.parent = node.left.right;
        tree.add(node);

        tree.traverse(n -> System.out.print(n + " "));
        tree.turnAnticlockwise(node.left);
        tree.traverse(n -> System.out.print(n + " "));
    }


    /**
     * 新增
     */
    public void add(T val) {
        if (val == null)
            throw new NullPointerException();
        add(new TreeNode<>(val));
    }

    public void add(TreeNode<T> node) {
        if (root == null) {
            root = node;
            node.black = true;
            return;
        }
        TreeNode<T> parent = doAdd(root, node);

        autoBalance(node);
    }

    private void autoBalance(TreeNode<T> node) {
        // 插入结点的父结点为黑结点, 直接返回
        TreeNode<T> parent = node.parent;
        if (parent == null) {
            node.black = true;
            return;
        }
        if (parent.black)
            return;
        TreeNode<T> uncle = parent.left == node ? parent.right : parent.left;
        // 叔叔结点存在并且为红结点
        if (uncle != null && !uncle.black) {
            parent.parent.black = false;
            parent.black = true;
            uncle.black = true;
            autoBalance(parent.parent);
            return;
        }

    }

    private TreeNode<T> doAdd(TreeNode<T> root, TreeNode<T> node) {
        int cmp = root.compareTo(node);
        if (cmp > 0) {
            if (root.right == null) {
                root.right = node;
                node.parent = root;
                return root;
            }
            return doAdd(root.right, node);
        }
        if (cmp < 0) {
            if (root.left == null) {
                root.left = node;
                node.parent = root;
                return root;
            }
            return doAdd(root.left, node);
        }
        root.val = node.val;
        return root;
    }

    /**
     * 左旋
     */
    public void turnAnticlockwise(TreeNode<T> node) {
        if (node.right == null)
            throw new RuntimeException("right child should not be null while turning anticlockwise");
        TreeNode<T> p = node.parent;
        TreeNode<T> newRoot = node.right;
        node.right = newRoot.left;
        if (newRoot.left != null) {
            newRoot.left.parent = node;
        }

        newRoot.left = node;
        node.parent = newRoot;

        if (p != null) {
            if (p.left == node)
                p.left = newRoot;
            else if (p.right == node)
                p.right = newRoot;
            newRoot.parent = p;
        } else {
            this.root = newRoot;
            this.root.parent = null;
        }

    }

    /**
     * 右旋
     */
    public void turnClockwise(TreeNode<T> node) {
        if (node.left == null)
            throw new RuntimeException("right child should not be null while turning clockwise");
        TreeNode<T> p = node.parent;
        TreeNode<T> newRoot = node.left;
        node.left = newRoot.right;
        if (newRoot.left != null) {
            newRoot.left.parent = node;
        }

        newRoot.right = node;
        node.parent = newRoot;

        if (p != null) {
            if (p.left == node)
                p.left = newRoot;
            else if (p.right == node)
                p.right = newRoot;
            newRoot.parent = p;
        } else {
            this.root = newRoot;
            this.root.parent = null;
        }
    }


    /**
     * 遍历
     */
    public void traverse(Consumer<T> consumer) {
        if (root == null)
            return;
        doTraverse(root, consumer);
    }

    /**
     * 中序遍历
     */
    private void doTraverse(TreeNode<T> node, Consumer<T> consumer) {
        if (node.left != null)
            doTraverse(node.left, consumer);
        consumer.accept(node.val);
        if (node.right != null)
            doTraverse(node.right, consumer);
    }

}

class TreeNode<T extends Comparable<T>> implements Comparable<TreeNode<T>> {
    public boolean black;

    public TreeNode<T> left;

    public TreeNode<T> right;

    public TreeNode<T> parent;

    public T val;

    public TreeNode(T val) {
        if (val == null)
            throw new NullPointerException();
        this.val = val;
    }

    @Override
    public String toString() {
        return val == null ? null : val.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            throw new NullPointerException();
        if (!(obj instanceof TreeNode))
            return false;
        TreeNode<T> node = (TreeNode<T>) obj;
        return val.equals(node.val);
    }

    @Override
    public int compareTo(TreeNode<T> node) {
        return val.compareTo(node.val);
    }
}