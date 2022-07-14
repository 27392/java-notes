package cn.haohaoli.book.headfirst.iterator.version3.impl;

import cn.haohaoli.book.headfirst.iterator.version3.MenuItem;
import lombok.RequiredArgsConstructor;

import java.util.Iterator;

/**
 * @author lwh
 */
@RequiredArgsConstructor
public class DinerMenuIterator implements Iterator<MenuItem> {

    private final MenuItem[] menuItems;
    private int              position = 0;

    @Override
    public boolean hasNext() {
        return position < menuItems.length && menuItems[position] != null;
    }

    @Override
    public MenuItem next() {
        return menuItems[position++];
    }
}
