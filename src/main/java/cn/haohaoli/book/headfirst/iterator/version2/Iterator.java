package cn.haohaoli.book.headfirst.iterator.version2;

/**
 * @author lwh
 */
public interface Iterator<E> {

    /**
     * 是否还存在对像
     *
     * @return
     */
    boolean hasNext();

    /**
     * 下一个对像
     *
     * @return
     */
    E next();

}
