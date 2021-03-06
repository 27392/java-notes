package cn.haohaoli.data.structure.linkedList;

import java.util.Iterator;

/**
 * TODO 链表
 *  优点: 真正的动态,不需要处理固定容量的问题
 *  缺点: 丧失了随机访问的能力
 * @author LiWenHao
 * @date 2019/6/27 16:08
 */
public class LinkedList<E> {

    /**
     * 头节点
     */
    private Node dummyHead;
    private int size;

    public LinkedList() {
        this.dummyHead = new Node(null, null);
        this.size = 0;
    }

    public LinkedList(E[] arr) {
        if (null == arr || arr.length == 0) {
            throw new IllegalArgumentException();
        }
        this.dummyHead = new Node(null, null);
        Node cur = this.dummyHead;
        for (int i = 0; i < arr.length; i++) {
            cur.next = new Node(arr[i]);
            cur = cur.next;
        }
    }

    public LinkedList(Iterable<E> iterable) {
        if (null == iterable) {
            throw new IllegalArgumentException();
        }
        this.dummyHead = new Node(null, null);
        Node cur = this.dummyHead;
        Iterator<E> iterator = iterable.iterator();
        while (iterator.hasNext()){
            cur.next = new Node(iterator.next());
            cur = cur.next;
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 在指定位置添加元素
     * @param index 下标
     * @param e     元素
     */
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index 不合法");
        }
        //上一个节点
        Node prev = dummyHead;
        for (int i = 0; i < index ; i++) {
            prev = prev.next;
        }
        /*
        Node node = new Node(e);
        //当前节点(node)的下一个节点(next)指向`上一个节点(prev)的下一个节点(next)`
        node.next = prev.next;
        //上一个节点(prev)的下一个节点(next)指向当前节点(node)
        prev.next = node;
        */

        //简写
        prev.next = new Node(e, prev.next);
        size++;
    }

    /**
     * 在链表头添加元素
     * @param e 元素
     */
    public void addFirst(E e){
        add(0, e);
    }

    /**
     * 在最后面添加元素
     * @param e 元素
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 获取指定位置元素
     * @param index 下标
     * @return  元素
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index 不合法");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    /**
     * 获取第一个元素
     * @return  元素
     */
    public E getFirst () {
        return get(0);
    }

    /**
     * 获取最后一个元素
     * @return  元素
     */
    public E getLast () {
        return get(size - 1);
    }

    /**
     * 修改指定位置元素
     * @param index 下标
     * @param e     元素
     */
    public void set(int index, E e){
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index 不合法");
        }
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    /**
     * 包含元素
     * @param e 元素
     * @return  包含 true/false
     */
    public boolean contains (E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            } else {
                cur = cur.next;
            }
        }
        return false;
    }

    /**
     * 删除指定位置元素
     * @param index 下标
     * @return      删除后的元素
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index 不合法");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;
        size--;
        return delNode.e;
    }

    /**
     * 删除第一个元素
     * @return  删除后的元素
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除最后一个元素
     * @return  删除后的元素
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 节点
     */
    private class Node{
        E e;
        Node next;

        Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            res.append(cur).append("->");
        }
        res.append("NULL");
        return res.toString();
    }
}
