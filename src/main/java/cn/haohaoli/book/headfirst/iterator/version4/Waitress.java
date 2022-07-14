package cn.haohaoli.book.headfirst.iterator.version4;

import cn.haohaoli.book.java8.chapter2.lambda.Predicate;
import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author lwh
 */
@RequiredArgsConstructor
public class Waitress {

    private final List<Menu> menus;

    /**
     * 打印所有的菜品
     */
    public void printMenu() {
        for (Menu menu : menus) {
            this.printMenu(menu.createIterator(), r -> true);
        }
    }

    /**
     * 打印所有的早餐
     */
    public void printBreakfastMenu() {
        for (Menu menu : menus) {
            this.printMenu(menu.createIterator(), r -> Objects.equals(r.getType(), MenuType.BREAKFAST));
        }
    }

    /**
     * 打印所有的午餐
     */
    public void printLunchMenu() {
        for (Menu menu : menus) {
            this.printMenu(menu.createIterator(), r -> Objects.equals(r.getType(), MenuType.LUNCH));
        }
    }

    /**
     * 打印所有的素食菜
     */
    public void printVegetarianMenu() {
        for (Menu menu : menus) {
            this.printMenu(menu.createIterator(), MenuItem::isVegetarian);
        }
    }

    private void printMenu(Iterator<MenuItem> iterator, Predicate<MenuItem> predicate) {
        while (iterator.hasNext()) {
            MenuItem menuItem = iterator.next();
            if (predicate.test(menuItem)) {
                System.out.println(menuItem.getName() + " :\t[" + menuItem.getPrice() + "]\t- " + menuItem.getDescription());
            }
        }
    }

}
