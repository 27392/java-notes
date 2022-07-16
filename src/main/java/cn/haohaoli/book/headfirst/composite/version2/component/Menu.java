package cn.haohaoli.book.headfirst.composite.version2.component;

import cn.haohaoli.book.headfirst.composite.version2.iterator.CompositeIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author lwh
 */
public class Menu extends MenuComponent {

    private final String name;
    private final String description;

    private final List<MenuComponent> list = new ArrayList<>();

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public void add(MenuComponent component) {
        list.add(component);
    }

    @Override
    public void remove(MenuComponent component) {
        list.remove(component);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void print() {
        System.out.print(" \n" + getName());
        System.out.print(", " + getDescription() + "\n");
        System.out.println("-------------------");
        for (MenuComponent component : list) {
            component.print();
        }
    }

    @Override
    public Iterator<MenuComponent> createIterator() {
        return new CompositeIterator(list.iterator());
    }

}
