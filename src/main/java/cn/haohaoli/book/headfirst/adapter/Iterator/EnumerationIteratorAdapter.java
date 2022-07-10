package cn.haohaoli.book.headfirst.adapter.Iterator;

import lombok.AllArgsConstructor;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * @author lwh
 */
@AllArgsConstructor
public class EnumerationIteratorAdapter<E> implements Iterator<E> {

    private final Enumeration<E> enumeration;

    @Override
    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    @Override
    public E next() {
        return enumeration.nextElement();
    }
}
