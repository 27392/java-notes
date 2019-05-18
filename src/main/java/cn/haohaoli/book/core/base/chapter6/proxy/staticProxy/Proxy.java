package cn.haohaoli.book.core.base.chapter6.proxy.staticProxy;

import cn.haohaoli.book.core.base.chapter6.proxy.Target;

/**
 * 静态代理
 * @author LiWenHao
 * @date 2019-02-05 20:10
 */
public class Proxy implements Target {

    private Target target;

    public Proxy(Target target) {
        this.target = target;
    }

    @Override
    public String execute() {
        before();
        String execute = this.target.execute();
        after();
        return execute;
    }

    private void before () {
        System.out.println("before...");
    }

    private void after() {
        System.out.println("after...");
    }

}
