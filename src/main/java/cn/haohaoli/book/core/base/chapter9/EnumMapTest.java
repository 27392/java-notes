package cn.haohaoli.book.core.base.chapter9;

import java.util.EnumMap;

/**
 * @author LiWenHao
 */
public class EnumMapTest extends EnumSetTest {

    public static void main(String[] args) {

        // EnumMap 内部使用数组实现,长度为枚举的个数
        EnumMap<Color, Integer> enumMap = new EnumMap<>(Color.class);
        enumMap.put(Color.WHITE, 0);
        enumMap.put(Color.BLACK, 1);
        enumMap.put(Color.GREEN, 2);

        enumMap.forEach((k,v)-> System.out.println(k + " - " + v));
    }
}
