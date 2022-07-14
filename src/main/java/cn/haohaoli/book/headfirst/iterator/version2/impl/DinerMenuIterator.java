package cn.haohaoli.book.headfirst.iterator.version2.impl;

import cn.haohaoli.book.headfirst.iterator.version2.Iterator;
import cn.haohaoli.book.headfirst.iterator.version2.MenuItem;
import lombok.RequiredArgsConstructor;

/**
 * @author lwh
 */
@RequiredArgsConstructor
public class DinerMenuIterator implements Iterator<MenuItem> {

    private final MenuItem[] menuItems;
    private int position = 0;

    @Override
    public boolean hasNext() {
        return position < menuItems.length && menuItems[position] != null;
    }

    @Override
    public MenuItem next() {
        return menuItems[position++];
    }
}
