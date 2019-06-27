package cn.haohaoli.data.structure.stack;

import cn.haohaoli.data.structure.linkedList.LinkedList;

/**
 * @author LiWenHao
 * @date 2019-06-27 23:36
 */
public class LinkedListStack<E> implements Stack<E> {

    LinkedList<E> list;

    public LinkedListStack() {
        this.list = new LinkedList<>();
    }

    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    @Override
    public E pop() {
        return list.removeFirst();
    }

    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return "Stack: top " + list;
    }
}
