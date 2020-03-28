package cn.haohaoli.book.core.base.chapter9;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author LiWenHao
 */
public class LinkedHashMapTest {

    public static void main(String[] args) {

        System.out.println("HashMap");
        Map<Integer, String> hashMap = new HashMap<>(8);
        hashMap.put(9, "1");
        hashMap.put(6, "1");
        hashMap.put(2, "1");
        hashMap.put(5, "1");

        hashMap.forEach((k, v) -> System.out.println(k + " - " + v));

        System.out.println("LinedHashMap");
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(9, "1");
        linkedHashMap.put(6, "1");
        linkedHashMap.put(2, "1");
        linkedHashMap.put(5, "1");
        linkedHashMap.forEach((k, v) -> System.out.println(k + " - " + v));
    }

}
