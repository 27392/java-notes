package cn.haohaoli.book.boast.simplefactory.impl;

import cn.haohaoli.book.boast.simplefactory.Algorithm;

/**
 * @author LiWenHao
 * @date 2019/2/25 11:25
 */
public class Subtraction extends Algorithm {

    @Override
    public String getResult() {
        return String.valueOf(Integer.parseInt(a) - Integer.parseInt(b));
    }
}
