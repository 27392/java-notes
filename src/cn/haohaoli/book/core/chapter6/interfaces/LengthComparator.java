package cn.haohaoli.book.core.chapter6.interfaces;

import java.util.Comparator;

/**
 * 比较器
 */
public class LengthComparator implements Comparator<String> {

    @Override
    public int compare(String first, String second) {
        return first.length() - second.length();
    }

}