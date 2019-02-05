package cn.haohaoli.book.core.chapter6.proxy.dynamicProxy;

import cn.haohaoli.book.core.chapter6.proxy.Target;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * @author LiWenHao
 * @date 2019-02-05 20:24
 */
public class DynamicProxy implements InvocationHandler {

    private Target target;

    public DynamicProxy(Target target) {
        this.target = target;
    }

    public Object newProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy.getClass().getName());
        before();
        Object invoke = method.invoke(target, args);
        after();
        return invoke;
    }


    private void before () {
        System.out.println("before...");
    }

    private void after() {
        System.out.println("after...");
    }
}
