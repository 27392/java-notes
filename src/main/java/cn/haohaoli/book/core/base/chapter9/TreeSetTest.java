package cn.haohaoli.book.core.base.chapter9;

import lombok.*;

import java.util.*;

/**
 * @author LiWenHao
 * @date 2019-05-26 17:50
 */
public class TreeSetTest {

    public static void main(String[] args) {
        Set<Item> parts = new TreeSet<>();
        parts.add(Item.of("Toaster", 1234));
        parts.add(Item.of("Modem", 9912));
        parts.add(Item.of("Widget", 4562));
        System.out.println(parts);

        // 当`Comparable`,`Comparator`同时存在时,使用给定的`Comparator`规则
        Set<Item> sortByDescription = new TreeSet<>(Comparator.comparing(Item::getDescription));
        sortByDescription.addAll(parts);
        System.out.println(sortByDescription);
    }

    @Getter
    @ToString
    @EqualsAndHashCode
    @AllArgsConstructor(staticName = "of")
    static class Item implements Comparable<Item> {

        private String description;
        private int partNumber;

        @Override
        public int compareTo(Item o) {
            int diff = Integer.compare(partNumber, o.partNumber);
            return diff != 0 ? diff : description.compareTo(o.description);
        }
    }
}
