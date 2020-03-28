package cn.haohaoli.book.core.base.chapter9;

import java.util.*;

/**
 * @author LiWenHao
 */
public class TreeMapTest extends TreeSetTest {

    public static void main(String[] args) {
        Map<Item, Integer> parts = new TreeMap<>();

        parts.put(Item.of("Toaster", 1234), 1);
        parts.put(Item.of("Modem", 9912), 2);
        parts.put(Item.of("Widget", 4562), 3);
        System.out.println(parts);

        Map<Item,Integer> sortByDescription = new TreeMap<>(Comparator.comparing(Item::getDescription));
        sortByDescription.putAll(parts);
        System.out.println(sortByDescription);
    }
}
