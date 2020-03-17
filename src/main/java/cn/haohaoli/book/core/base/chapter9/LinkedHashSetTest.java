package cn.haohaoli.book.core.base.chapter9;

import java.util.*;

/**
 * @author LiWenHao
 */
public class LinkedHashSetTest {

    public static void main(String[] args) {

        List<String> strs = Arrays.asList("a", "z", "d", "c");
        System.out.println("原始数据 : " + strs);

        // LinkedHashSet 能记住添加的数据的顺序,并不是真正意义上有序.
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>(strs);
        System.out.println("LinkedHashSet : " + linkedHashSet);

        Set<String> set = new HashSet<>(strs);
        System.out.println("HashSet : " + set);
    }
}
