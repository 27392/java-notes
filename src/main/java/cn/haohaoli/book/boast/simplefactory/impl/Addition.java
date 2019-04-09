package cn.haohaoli.book.boast.simplefactory.impl;

import cn.haohaoli.book.boast.simplefactory.Algorithm;

/**
 * 加法
 * @author LiWenHao
 * @date 2019/2/25 11:22
 */
public class Addition extends Algorithm {

    @Override
    public String getResult() {
        return String.valueOf(Integer.parseInt(a) + Integer.parseInt(b));
    }
}
