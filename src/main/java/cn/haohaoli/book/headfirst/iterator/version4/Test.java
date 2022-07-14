package cn.haohaoli.book.headfirst.iterator.version4;

import cn.haohaoli.book.headfirst.iterator.version4.menu.CafeMenu;
import cn.haohaoli.book.headfirst.iterator.version4.menu.DinerMenu;
import cn.haohaoli.book.headfirst.iterator.version4.menu.PancakeHouseMenu;

import java.util.Arrays;
import java.util.List;

/**
 * @author lwh
 */
public class Test {

    public static void main(String[] args) {
        List<Menu> menus  = Arrays.asList(new DinerMenu(), new PancakeHouseMenu(), new CafeMenu());
        Waitress waitress = new Waitress(menus);

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
