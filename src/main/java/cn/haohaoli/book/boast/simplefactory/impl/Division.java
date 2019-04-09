package cn.haohaoli.book.boast.simplefactory.impl;

import cn.haohaoli.book.boast.simplefactory.Algorithm;

/**
 * @author LiWenHao
 * @date 2019/2/25 11:27
 */
public class Division extends Algorithm {
    @Override
    public String getResult() {
        if (b.equals("0")) {
            throw new RuntimeException("除数不能为零！");
        }
        return String.valueOf(Integer.parseInt(a) / Integer.parseInt(b));
    }
}
