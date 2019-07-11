package cn.haohaoli.data.structure.bst;

/**
 * TODO 二分搜索树
 *  二分搜索树是二叉树
 *  二分搜索树的每个节点的值
 *      * 大于其左子树的所有节点的值
 *      * 小于其右子树的所有节点的值
 * @author LiWenHao
 * @date 2019/7/11 15:14
 */
public class BST<E extends Comparable<E>> {

    private Node root;
    private int size;

    private class Node {
        E e;
        Node left;
        Node right;

        Node(E e) {
            this.e = e;
        }
    }

    /**
     * 添加元素
     * @param e
     */
    public void add (E e) {
        root = add(root, e);
    }

    /**
     * 向以node为根的二分搜索数中插入元素e, 递归算法
     * 返回插入新节点后二分搜索数的根
     * @see cn.haohaoli.book.core.base.chapter6.interfaces.Employee#compareTo
     * @param node  node节点
     * @param e     元素
     * @return      插入新节点后二分搜索数的根
     */
    private Node add (Node node, E e) {
        if (null == node) {
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else {
            node.right = add(node.right, e);
        }
        return node;
    }

    /**
     * 二分搜索数中是否保存元素
     * @param e
     * @return
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 向以node为根的二分搜索数中是否包含元素e, 递归算法
     * @param node
     * @param e
     * @return
     */
    private boolean contains(Node node, E e) {
        if (null == node) {
            return false;
        }
        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
