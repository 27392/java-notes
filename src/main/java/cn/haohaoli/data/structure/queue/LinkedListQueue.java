package cn.haohaoli.data.structure.queue;

/**
 * TODO 链表队列
 *  入队:
 *      增加`tail`变量，记录尾部节点位置,所以添加元素的话不管在头或者尾都是很方便
 *  出队:
 *      如果使用链表头做`队首`,使用链表尾`队尾`
 *          要删除链表的尾节点需要找到该节点的前一个节点,则需要遍历
 *          无法用O(1)复杂度删除该元素
 *      如果使用链表头做`队尾`,使用链表尾`队首`
 *          删除链表头节点的元素
 *              头节点的next等于头节
 *              head = head.next;
 *          能使用O(1)复杂度删除该元素
 * @author LiWenHao
 * @date 2019/6/28 11:22
 */
public class LinkedListQueue<E> implements Queue<E> {

    private Node head;
    private Node tail;
    private int size;

    @Override
    public void enqueue(E e) {
        //在尾部添加
        Node node = new Node(e);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            //当前尾节点(tail)的下一个节点(next)指向节点(node)
            tail.next = node;
            //当前尾节点(tail)指向当前节点(tail)的下一个节点(next)
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("链表为空");
        }
        //删除的节点
        Node delNode = head;
        //当前头节点(head)等于当前头节点(head)的下一个节点(next)
        head = head.next;
        //去除节点在的链表中的指向
        delNode.next = null;
        size--;
        //如果链表为空 需要把尾节点赋值为空
        if (isEmpty()) {
            tail = null;
        }
        return delNode.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("链表为空");
        }
        return head.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private class Node {
        E e;
        Node next;

        Node(E e) {
            this.e = e;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        Node cur = head;
        for (int i = 0; i < size; i++) {
            res.append(cur.e);
            cur = cur.next;
            res.append("->");
        }
        res.append("NULL] tail");
        return res.toString();
    }
}
