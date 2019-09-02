package cn.haohaoli.book.core.base.chapter6.interfaces;

import java.util.Comparator;

/**
 * TODO Comparator 接口
 *  定义对String长度的比较 重写compare方法
 * @author LiWenHao
 * @date 2019-02-03 16:01
 */
public class LengthComparator implements Comparator<String> {

    @Override
    public int compare(String first, String second) {
        return first.length() - second.length();
    }
}