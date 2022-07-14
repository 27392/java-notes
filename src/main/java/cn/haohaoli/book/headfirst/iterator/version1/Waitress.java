package cn.haohaoli.book.headfirst.iterator.version1;

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
        MenuItem[]     menuItems  = dinerMenu.getMenuItems();
        List<MenuItem> menuItemList = pancakeHouseMenu.getMenuItems();

        System.out.println("---------- 早餐 ----------");
        for (int i = 0; i < menuItemList.size(); i++) {
            MenuItem menuItem = menuItemList.get(i);
            System.out.println(menuItem.getName() + " :\t[" + menuItem.getPrice() + "]\t- " + menuItem.getDescription());
        }
        System.out.println("---------- 午餐 ----------");
        for (int i = 0; i < dinerMenu.getNumberOfItems(); i++) {
            MenuItem menuItem = menuItems[i];
            System.out.println(menuItem.getName() + " :\t[" + menuItem.getPrice() + "]\t- " + menuItem.getDescription());
        }
    }

    /**
     * 打印所有的早餐
     */
    public void printBreakfastMenu() {
        List<MenuItem> menuItemList = pancakeHouseMenu.getMenuItems();
        for (int i = 0; i < menuItemList.size(); i++) {
            MenuItem menuItem = menuItemList.get(i);
            System.out.println(menuItem.getName() + " :\t[" + menuItem.getPrice() + "]\t- " + menuItem.getDescription());
        }
    }

    /**
     * 打印所有的午餐
     */
    public void printLunchMenu() {
        MenuItem[]     menuItems  = dinerMenu.getMenuItems();
        for (int i = 0; i < dinerMenu.getNumberOfItems(); i++) {
            MenuItem menuItem = menuItems[i];
            System.out.println(menuItem.getName() + " :\t[" + menuItem.getPrice() + "]\t- " + menuItem.getDescription());
        }
    }

    /**
     * 打印所有的素食菜
     */
    public void printVegetarianMenu() {
        MenuItem[]     menuItems  = dinerMenu.getMenuItems();
        List<MenuItem> menuItemList = pancakeHouseMenu.getMenuItems();

        for (int i = 0; i < menuItemList.size(); i++) {
            MenuItem menuItem = menuItemList.get(i);
            if (menuItem.isVegetarian()) {
                System.out.println(menuItem.getName() + " :\t[" + menuItem.getPrice() + "]\t- " + menuItem.getDescription());
            }
        }
        for (int i = 0; i < dinerMenu.getNumberOfItems(); i++) {
            MenuItem menuItem = menuItems[i];
            if (menuItem.isVegetarian()) {
                System.out.println(menuItem.getName() + " :\t[" + menuItem.getPrice() + "]\t- " + menuItem.getDescription());
            }
        }
    }

    /**
     * 是否是素食
     */
    public boolean isItemVegetarian(MenuItem item) {
        return item.isVegetarian();
    }

}
