package cn.haohaoli.book.headfirst.composite.version2.iterator;

import cn.haohaoli.book.headfirst.composite.version2.component.MenuComponent;

import java.util.Iterator;

/**
 * 空迭代器
 * <p>
 * 永远返回false, 避免客户端进行空判断
 *
 * @author lwh
 */
public class NullIteIterator implements Iterator<MenuComponent> {

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public MenuComponent next() {
        return null;
    }
}
