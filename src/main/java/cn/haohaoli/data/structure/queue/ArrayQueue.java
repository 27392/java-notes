package cn.haohaoli.data.structure.queue;

import cn.haohaoli.data.structure.array.Array;

/**
 * TODO 数组队列
 *  选择数组的最后添加元素是因为在数组头添加元素的话之后的所有元素都会往后移动
 *  空间复杂度为O(n)
 *  在数组最后添加元素的就不用移动元素
 *  空间复杂度为O(1)
 * @author LiWenHao
 * @date 2019-06-25 21:04
 */
public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    public ArrayQueue() {
        this.array = new Array<>();
    }

    public ArrayQueue(int capacity) {
        this.array = new Array<>(capacity);
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFist();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: ");
        sb.append("front [");
        for (int i = 0; i < array.getSize(); i++) {
            sb.append(array.get(i));
            if (i != array.getSize() - 1) {
                sb.append(", ");
            }
        }
        sb.append("] tail");
        return sb.toString();
    }
}
