package cn.haohaoli.book.headfirst.iterator.version2.impl;

import cn.haohaoli.book.headfirst.iterator.version2.Iterator;
import cn.haohaoli.book.headfirst.iterator.version2.MenuItem;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author lwh
 */
@RequiredArgsConstructor
public class PancakeHouseIterator implements Iterator<MenuItem> {

    private final List<MenuItem> menuItemList;
    private       int            position = 0;

    @Override
    public boolean hasNext() {
        return position < menuItemList.size() && menuItemList.get(position) != null;
    }

    @Override
    public MenuItem next() {
        return menuItemList.get(position++);
    }
}
