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
     * 在链表头添加元素
     * @param e 元素
     */
    public void addFirst(E e){
        /*
        Node node = new Node(e);
        //当前节点(node)的下一个节点(next)指向`当前头节点(head)`
        node.next = head;
        //`当前头节点(head)`指向`当前节点(node)`
        head = node;
        */

        //简写
        head = new Node(e, head);
        size++;
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
        if(index == 0) {
            addFirst(e);
        } else {
            //上一个节点
            Node prev = head;
            for (int i = 0; i < index - 1; i++) {
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
    }

    /**
     * 在最后面添加元素
     * @param e 元素
     */
    public void addLast(E e) {
        add(size, e);
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
}
