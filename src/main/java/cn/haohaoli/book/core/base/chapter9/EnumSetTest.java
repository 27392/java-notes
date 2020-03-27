package cn.haohaoli.book.core.base.chapter9;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

/**
 * @author LiWenHao
 */
public class EnumSetTest {

    public static void main(String[] args) {

        // 创建指定元素的EnumSet
        EnumSet<Color> of = EnumSet.of(Color.WHITE, Color.GREEN);
        System.out.println("of     : " + of);

        // 创建不包含元素的EnumSet
        System.out.println("noneOf : " + EnumSet.noneOf(Color.class));

        // 创建包含所有元素的EnumSet
        System.out.println("allOf  : " + EnumSet.allOf(Color.class));

        // 创建指定范围的元素的EnumSet
        System.out.println("range  : " + EnumSet.range(Color.BLUE, Color.WHITE));

        // 取反
        System.out.println("complementOf : " + EnumSet.complementOf(of));

        // 拷贝EnumSet
        System.out.println("copyOf : " + EnumSet.copyOf(of));

        // 拷贝集合至EnumSet
        List<Color> colors = Arrays.asList(Color.WHITE, Color.WHITE, Color.BLACK,Color.YELLOW);
        System.out.println("copyOf : " + EnumSet.copyOf(colors));
    }

    private enum Color{

        BLUE,
        GREEN,
        YELLOW,
        WHITE,
        BLACK

    }
}
