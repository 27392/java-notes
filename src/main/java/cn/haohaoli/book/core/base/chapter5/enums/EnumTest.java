package cn.haohaoli.book.core.base.chapter5.enums;

import java.util.Scanner;

/**
 * 枚举测试类
 * @author LiWenHao
 * @date 2019/1/24 11:23
 */
public class EnumTest {

    /**
     * TODO Enum (部分) API:
     *  • static Enum valueOf(Class enumClass, String name)
     *      返回指定名字、给定类的枚举常量。
     *  • String toString()
     *      返回枚举常量名。
     *  • int ordinal ()
     *      返回枚举常量在 enum 声明中的位置，位置从 0 开始计数。
     *  • int compareTo(E other)
     *      如果枚举常量出现在 Other 之前， 则返回一个负值；如果 this=other，则返回 0; 否则，
     *      返回正值。枚举常量的出现次序在 enum 声明中给出
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a size: (SMALL, MEDIUM, LARGE, EXTRA_LARGE) ");
        String input = in.next().toUpperCase();
        Size size = Size.valueOf(Size.class, input);
        System.out.println("size= " + size);
        System.out.println("abbreviation= " + size.getAbbreviation());
        if (size == Size.SMALL) {
            System.out.println(size.getAbbreviation());
        }
        //每个枚举类型都有一个静态的 values 方法， 它将返回一个包含全部枚举值的数组
        Size[] values = Size.values();
        for (Size value : values) {
            System.out.printf("%s  ",value);
        }
    }
}
