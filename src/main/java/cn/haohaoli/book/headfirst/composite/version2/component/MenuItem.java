package cn.haohaoli.book.headfirst.composite.version2.component;

import cn.haohaoli.book.headfirst.composite.version2.iterator.NullIteIterator;
import lombok.AllArgsConstructor;

import java.util.Iterator;

/**
 * @author lwh
 */
@AllArgsConstructor
public class MenuItem extends MenuComponent {

    private String   name;
    private String   description;
    private boolean  vegetarian;
    private double   price;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public boolean isVegetarian() {
        return vegetarian;
    }

    @Override
    public void print() {
        System.out.print(" " + this.getName());
        if (isVegetarian()) {
            System.out.print("(素食)");
        }
        System.out.println(", "+ this.getPrice());
        System.out.println("    -- " + this.getDescription());
    }

    @Override
    public Iterator<MenuComponent> createIterator() {
        return new NullIteIterator();
    }
}
