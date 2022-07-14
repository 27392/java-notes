package cn.haohaoli.book.headfirst.iterator.version3;

import lombok.RequiredArgsConstructor;

import java.util.Iterator;

/**
 * @author lwh
 */
@RequiredArgsConstructor
public class Waitress {

    private final Menu dinerMenu;
    private final Menu pancakeHouseMenu;
    private final Menu cafeMenu;

    /**
     * 打印所有的菜品
     */
    public void printMenu() {
        Iterator<MenuItem> dinerMenuIterator    = dinerMenu.createIterator();
        Iterator<MenuItem> pancakeHouseIterator = pancakeHouseMenu.createIterator();
        Iterator<MenuItem> cafeMenuIterator     = cafeMenu.createIterator();

        System.out.println("---------- 早餐 ----------");
        printMenu(pancakeHouseIterator, false);

        System.out.println("---------- 午餐 ----------");
        printMenu(dinerMenuIterator, false);

        System.out.println("---------- 晚餐 ----------");
        printMenu(cafeMenuIterator, false);
    }

    /**
     * 打印所有的早餐
     */
    public void printBreakfastMenu() {
        printMenu(pancakeHouseMenu.createIterator(), false);
    }

    /**
     * 打印所有的午餐
     */
    public void printLunchMenu() {
        printMenu(dinerMenu.createIterator(), false);
    }

    /**
     * 打印所有的素食菜
     */
    public void printVegetarianMenu() {
        printMenu(dinerMenu.createIterator(), true);
        printMenu(pancakeHouseMenu.createIterator(), true);
        printMenu(cafeMenu.createIterator(), true);
    }


    private void printMenu(Iterator<MenuItem> iterator, boolean filterVegetarian) {
        while (iterator.hasNext()) {
            MenuItem menuItem = iterator.next();
            if (filterVegetarian) {
                if (menuItem.isVegetarian()) {
                    System.out.println(menuItem.getName() + " :\t[" + menuItem.getPrice() + "]\t- " + menuItem.getDescription());
                }
            } else {
                System.out.println(menuItem.getName() + " :\t[" + menuItem.getPrice() + "]\t- " + menuItem.getDescription());
            }
        }
    }

}
