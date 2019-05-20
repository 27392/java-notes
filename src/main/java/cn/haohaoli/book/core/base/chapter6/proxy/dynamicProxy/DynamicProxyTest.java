package cn.haohaoli.book.core.base.chapter6.proxy.dynamicProxy;

import cn.haohaoli.book.core.base.chapter6.proxy.Target;
import cn.haohaoli.book.core.base.chapter6.proxy.TargetImpl;

/**
 * 代理测试类
 * @author LiWenHao
 * @date 2019-02-05 17:22
 */
public class DynamicProxyTest {

    public static void main(String[] args) {
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        Target target = new TargetImpl();
        DynamicProxy dynamicProxy = new DynamicProxy(target);
        Target o = (Target) dynamicProxy.newProxyInstance();
        String execute = o.execute();
    }
}
