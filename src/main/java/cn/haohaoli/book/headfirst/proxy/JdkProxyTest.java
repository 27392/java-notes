package cn.haohaoli.book.headfirst.proxy;

import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 *
 * @author lwh
 */
public class JdkProxyTest {

    public static void main(String[] args) {

        // 是否需要将生成的代理类Class文件保存到本地
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        // 目标对象
        UserService service = new UserServiceImpl();

        // 代理service对象,传入(classLoader、service对象的接口数组、invocationHandler)
        UserService proxy = (UserService) Proxy.newProxyInstance(JdkProxyTest.class.getClassLoader(), new Class[]{UserService.class}, new UserServiceInvocationHandler(service));

        // 使用代理调用方法
        boolean bol = proxy.deleteById("1");
    }

    interface UserService {
        boolean deleteById(String id);
    }

    static class UserServiceImpl implements UserService {

        @Override
        public boolean deleteById(String id) {
            System.out.println("deleteById: " + id);
            return false;
        }
    }

    /**
     * UserServiceInvocationHandler
     */
    @RequiredArgsConstructor
    static class UserServiceInvocationHandler implements InvocationHandler {

        private final UserService target;

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            this.before();
            Object invoke = method.invoke(target, args);
            // 注意方法调用时请勿使用代理对象，否在会发生递归并产生StackOverflowError异常
            // Object invoke = method.invoke(proxy, args);
            this.after();
            return invoke;
        }

        private void before() {
            System.out.println("校验权限...");
        }

        private void after() {
            System.out.println("记录日志...");
        }
    }
}
