package cn.haohaoli.book.core.base.chapter6.interfaces;

import java.util.Arrays;

/**
 * Comparator(比较器) 测试类
 * @author LiWenHao
 * @date 2019-02-03 16:01
 */
public class ComparatorTest {

    public static void main(String[] args) {
        String[] s = {"x","xxxx","xx"};
        Arrays.sort(s, new LengthComparator());
        // 以下是 java8 实现
//        Arrays.sort(s, ((first, second) -> first.length() - second.length()));
//        Arrays.sort(s, Comparator.comparing(String::length));
        System.out.println(Arrays.toString(s));
    }
}