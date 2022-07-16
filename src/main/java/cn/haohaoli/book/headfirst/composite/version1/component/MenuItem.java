package cn.haohaoli.book.headfirst.composite.version1.component;

import lombok.AllArgsConstructor;

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
}
