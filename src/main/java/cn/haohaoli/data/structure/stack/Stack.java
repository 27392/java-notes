package cn.haohaoli.data.structure.stack;

/**
 * @author LiWenHao
 * @date 2019-06-24 23:31
 */
public interface Stack<E> {

    /**
     * 入栈
     * @param e 元素
     */
    void push(E e);

    /**
     * 出栈
     * @return  元素
     */
    E pop();

    /**
     * 查看栈顶元素
     * @return  元素
     */
    E peek();

    /**
     * 获取元素大小
     * @return  大小
     */
    int getSize();

    /**
     * 是否为空
     * @return
     */
    boolean isEmpty();
}
