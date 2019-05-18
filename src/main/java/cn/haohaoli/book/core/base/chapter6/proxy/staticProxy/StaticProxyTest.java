package cn.haohaoli.book.core.base.chapter6.proxy.staticProxy;

import cn.haohaoli.book.core.base.chapter6.proxy.Target;
import cn.haohaoli.book.core.base.chapter6.proxy.TargetImpl;

/**
 * @author LiWenHao
 * @date 2019-02-05 20:16
 */
public class StaticProxyTest {

    public static void main(String[] args) {

        Target target = new TargetImpl();
        Proxy proxy = new Proxy(target);
        proxy.execute();
    }
}
