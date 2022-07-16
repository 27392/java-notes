package cn.haohaoli.book.headfirst.composite.version1;

import cn.haohaoli.book.headfirst.composite.version1.component.MenuComponent;
import cn.haohaoli.book.headfirst.iterator.version4.Menu;
import cn.haohaoli.book.headfirst.iterator.version4.MenuItem;
import cn.haohaoli.book.headfirst.iterator.version4.MenuType;
import cn.haohaoli.book.java8.chapter2.lambda.Predicate;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author lwh
 */
@AllArgsConstructor
public class Waitress {

    private MenuComponent allMenus;

    /**
     * 打印所有的菜品
     */
    public void printMenu() {
        allMenus.print();
    }


}
