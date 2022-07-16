package cn.haohaoli.book.headfirst.composite.version2.iterator;

import cn.haohaoli.book.headfirst.composite.version2.component.MenuComponent;
import cn.haohaoli.book.headfirst.composite.version2.component.Menu;
import lombok.AllArgsConstructor;

import java.util.Iterator;
import java.util.Stack;

/**
 * @author lwh
 */
public class CompositeIterator implements Iterator<MenuComponent> {

    private final Stack<Iterator<MenuComponent>> stack = new Stack<>();

    public CompositeIterator(Iterator<MenuComponent> iterator) {
        stack.push(iterator);
    }

    @Override
    public boolean hasNext() {
        if (stack.isEmpty()) {
            return false;
        }
        Iterator<MenuComponent> iterator = stack.peek();
        if (iterator.hasNext()) {
            return true;
        }
        stack.pop();
        return hasNext();
    }

    @Override
    public MenuComponent next() {
        if(this.hasNext()){
            MenuComponent component = stack.peek().next();
            if (component instanceof Menu) {
                stack.push(component.createIterator());
            }
            return component;
        }
        return null;
    }
}
