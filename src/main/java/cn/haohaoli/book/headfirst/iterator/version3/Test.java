package cn.haohaoli.book.headfirst.iterator.version3;

import cn.haohaoli.book.headfirst.iterator.version3.menu.CafeMenu;
import cn.haohaoli.book.headfirst.iterator.version3.menu.DinerMenu;
import cn.haohaoli.book.headfirst.iterator.version3.menu.PancakeHouseMenu;

/**
 * @author lwh
 */
public class Test {

    public static void main(String[] args) {
        Waitress waitress = new Waitress(new DinerMenu(), new PancakeHouseMenu(), new CafeMenu());

        System.out.println("=== 所有菜品 ===");
        waitress.printMenu();

        System.out.println("=== 早餐 ===");
        waitress.printBreakfastMenu();

        System.out.println("=== 午餐 ===");
        waitress.printLunchMenu();

        System.out.println("=== 素食餐 ===");
        waitress.printVegetarianMenu();
    }
}
