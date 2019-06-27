package cn.haohaoli.data.structure.queue;

/**
 * TODO 队列
 *  队列是一种先进先出的数据结构(先到先得)
 *  first in first out (FIFO)
 * @author LiWenHao
 * @date 2019-06-25 21:00
 */
public interface Queue<E> {

    /**
     * 入队
     * @param e 元素
     */
    void enqueue(E e);

    /**
     * 出队
     * @return  元素
     */
    E dequeue();

    /**
     * 获取队首的元素
     * @return  元素
     */
    E getFront();

    /**
     * 获取大小
     * @return
     */
    int getSize();

    /**
     * 是否为空
     * @return
     */
    boolean isEmpty();
}
