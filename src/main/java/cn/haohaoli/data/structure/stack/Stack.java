package cn.haohaoli.data.structure.stack;

/**
 * TODO 栈
 *  栈是一种线性结构
 *  栈是一种后进先出的数据结构
 *  相比数组,栈对应的操作是数组的子集
 *  last in first out (LIFO)
 *      只能从一端添加元素,也只能从一端取出元素
 *      这一端称为栈顶
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
