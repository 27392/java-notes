package cn.haohaoli.book.headfirst.iterator.version2;

import cn.haohaoli.book.headfirst.iterator.version2.menu.DinerMenu;
import cn.haohaoli.book.headfirst.iterator.version2.menu.PancakeHouseMenu;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author lwh
 */
@RequiredArgsConstructor
public class Waitress {

    private final DinerMenu        dinerMenu;
    private final PancakeHouseMenu pancakeHouseMenu;

    /**
     * 打印所有的菜品
     */
    public void printMenu() {
        Iterator<MenuItem> dinerMenuIterator = dinerMenu.createIterator();
        Iterator<MenuItem> pancakeHouseIterator = pancakeHouseMenu.createIterator();

        System.out.println("---------- 早餐 ----------");
        while (pancakeHouseIterator.hasNext()){
            MenuItem menuItem = pancakeHouseIterator.next();
            System.out.println(menuItem.getName() + " :\t[" + menuItem.getPrice() + "]\t- " + menuItem.getDescription());
        }

        System.out.println("---------- 午餐 ----------");
        while (dinerMenuIterator.hasNext()){
            MenuItem menuItem = dinerMenuIterator.next();
            System.out.println(menuItem.getName() + " :\t[" + menuItem.getPrice() + "]\t- " + menuItem.getDescription());
        }
    }

    /**
     * 打印所有的早餐
     */
    public void printBreakfastMenu() {
        Iterator<MenuItem> pancakeHouseIterator = pancakeHouseMenu.createIterator();
        while (pancakeHouseIterator.hasNext()){
            MenuItem menuItem = pancakeHouseIterator.next();
            System.out.println(menuItem.getName() + " :\t[" + menuItem.getPrice() + "]\t- " + menuItem.getDescription());
        }
    }

    /**
     * 打印所有的午餐
     */
    public void printLunchMenu() {
        Iterator<MenuItem> dinerMenuIterator = dinerMenu.createIterator();
        while (dinerMenuIterator.hasNext()){
            MenuItem menuItem = dinerMenuIterator.next();
            System.out.println(menuItem.getName() + " :\t[" + menuItem.getPrice() + "]\t- " + menuItem.getDescription());
        }
    }

    /**
     * 打印所有的素食菜
     */
    public void printVegetarianMenu() {
        Iterator<MenuItem> dinerMenuIterator = dinerMenu.createIterator();
        Iterator<MenuItem> pancakeHouseIterator = pancakeHouseMenu.createIterator();

        while (pancakeHouseIterator.hasNext()){
            MenuItem menuItem = pancakeHouseIterator.next();
            if (menuItem.isVegetarian()) {
                System.out.println(menuItem.getName() + " :\t[" + menuItem.getPrice() + "]\t- " + menuItem.getDescription());
            }
        }

        while (dinerMenuIterator.hasNext()){
            MenuItem menuItem = dinerMenuIterator.next();
            if (menuItem.isVegetarian()) {
                System.out.println(menuItem.getName() + " :\t[" + menuItem.getPrice() + "]\t- " + menuItem.getDescription());
            }
        }
    }

}
