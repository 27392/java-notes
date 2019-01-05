package cn.haohaoli.book.core.chapter5.interfaces;

import java.util.Arrays;

public class ComparatorTest {
    public static void main(String[] args) {
        String[] s = {"x","xxxxx","xxx"};
        Arrays.sort(s, new LengthComparator());
        // 以下是 java8 实现
//        Arrays.sort(s, ((first, second) -> first.length() - second.length()));
//        Arrays.sort(s, Comparator.comparing(String::length));
        System.out.println(Arrays.toString(s));
    }
}