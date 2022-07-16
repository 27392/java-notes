package cn.haohaoli.book.headfirst.composite.version1.component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lwh
 */
public class Menu extends MenuComponent {

    private String name;
    private String description;

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
}
