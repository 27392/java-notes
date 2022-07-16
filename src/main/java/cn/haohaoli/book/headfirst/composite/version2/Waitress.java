package cn.haohaoli.book.headfirst.composite.version2;

import cn.haohaoli.book.headfirst.composite.version2.component.MenuComponent;
import cn.haohaoli.book.headfirst.iterator.version4.Menu;
import cn.haohaoli.book.headfirst.iterator.version4.MenuItem;
import lombok.AllArgsConstructor;

import java.util.Iterator;

/**
 * @author lwh
 */
@AllArgsConstructor
public class Waitress {

    private final MenuComponent allMenus;

    /**
     * 打印所有的菜品
     */
    public void printMenu() {
        allMenus.print();
    }

    /**
     * 打印所有的素食菜
     */
    public void printVegetarianMenu() {
        Iterator<MenuComponent> iterator = allMenus.createIterator();
        while (iterator.hasNext()) {
            MenuComponent component = iterator.next();
            try {
                if (component.isVegetarian()) {
                    component.print();
                }
            } catch (UnsupportedOperationException e) {
                // ignore
            }
        }
    }

}
