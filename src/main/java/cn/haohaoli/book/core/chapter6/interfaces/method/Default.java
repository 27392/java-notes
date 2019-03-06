package cn.haohaoli.book.core.chapter6.interfaces.method;

/**
 * TODO 接口默认方法
 *  可以为接口方法提供一个默认实现。 必须用 default 修饰符标记这样一个方法
 * @author LiWenHao
 * @date 2019-02-03 15:07
 */
public interface Default<T> {

    default int compareTo(T other){
        return 0;
    }
}
