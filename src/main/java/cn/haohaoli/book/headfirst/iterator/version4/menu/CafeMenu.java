package cn.haohaoli.book.headfirst.iterator.version4.menu;

import cn.haohaoli.book.headfirst.iterator.version4.Menu;
import cn.haohaoli.book.headfirst.iterator.version4.MenuItem;
import cn.haohaoli.book.headfirst.iterator.version4.MenuType;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 煎饼屋菜单
 *
 * @author lwh
 */
public class CafeMenu implements Menu {

    private Map<String, MenuItem> menuItems;

    public CafeMenu() {
        this.menuItems = new HashMap<>();
        this.addItem("黑糖玛奇朵", "黑糖玛奇朵", true, 3.99);
        this.addItem("冰美式", "冰美式", false, 3.69);
        this.addItem("黑咖啡", "黑咖啡", true, 4.29);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        menuItems.put(name, new MenuItem(name, description, vegetarian, price, MenuType.DINNER));
    }

    public Iterator<MenuItem> createIterator() {
        return menuItems.values().iterator();
    }
}
