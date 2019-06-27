package cn.haohaoli.data.structure.linkedList;

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
    private Node head;
    private int size;

    public int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 节点
     */
    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next) {
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
}
