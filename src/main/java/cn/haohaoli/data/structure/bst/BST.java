package cn.haohaoli.data.structure.bst;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * TODO 二分搜索树
 *  二分搜索树是二叉树
 *  二分搜索树的每个节点的值
 *      * 大于其左子树的所有节点的值
 *      * 小于其右子树的所有节点的值
 *  排序参考: https://blog.csdn.net/u013834525/article/details/80421684
 * @author LiWenHao
 * @date 2019/7/11 15:14
 */
public class BST<E extends Comparable<E>> {

    private Node root;
    private int size;

    public BST() {
    }

    public BST(Iterable<E> iterable) {
        Iterator<E> iterator = iterable.iterator();
        while (iterator.hasNext()){
            add(iterator.next());
        }
    }

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
     * 二分搜索树中是否保存元素
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

    /**
     * 二分搜索树前序遍历
     */
    public void preOrder(){
        preOrder(root);
    }

    /**
     * 二分搜索树中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * 二分搜索树后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * 二分搜索树层序遍历
     */
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            Node cur = queue.remove();
            System.out.println(cur.e);
            if (null != cur.left) {
                queue.add(cur.left);
            }
            if (null != cur.right) {
                queue.add(cur.right);
            }
        }
    }

    /**
     * 前序遍历以node为根的二分搜索树,递归算法
     * @param node
     */
    private void preOrder(Node node){
        if (null == node) {
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历以node为根的二分搜索树,递归算法
     * @param node
     */
    private void inOrder(Node node) {
        if (null == node) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    /**
     * 后序遍历以node为根的二分搜索树,递归算法
     * @param node
     */
    private void postOrder(Node node) {
        if (null == node) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * 非递归实现前序遍历(使用栈)
     */
    public void preOrderNR(){
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()){
            Node pop = stack.pop();
            System.out.println(pop.e);
            //栈是(INFO 后进先出) 所以右孩子先入栈
            if (null != pop.right) {
                stack.add(pop.right);
            }
            if (null != pop.left) {
                stack.add(pop.left);
            }
        }
    }

    /**
     * 获取大小
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    /**
     * 生成树字符串
     * @param root
     * @param depth
     * @param res
     */
    private void generateBSTString(Node root, int depth, StringBuilder res){
        if (root == null) {
            res.append(generateDepthString(depth)).append("NULL\n");
            return;
        }
        res.append(generateDepthString(depth)).append(root.e).append("\n");
        generateBSTString(root.left, depth + 1, res);
        generateBSTString(root.right, depth + 1, res);
    }

    /**
     * 生成深度字符串
     * @param depth
     * @return
     */
    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }
}
