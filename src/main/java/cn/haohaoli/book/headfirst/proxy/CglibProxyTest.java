package cn.haohaoli.book.headfirst.proxy;

import lombok.RequiredArgsConstructor;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib动态代理
 *
 * @author lwh
 */
public class CglibProxyTest {

    public static void main(String[] args) {

        // 将生成的代理类class文件保存到指定位置
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, CglibProxyTest.class.getClassLoader().getResource("").getPath());

        // Enhancer对象负责创建代理对象,与JDK中的Proxy类似
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);    // 设置父类
        enhancer.setCallback(new UserServiceMethodInterceptor());    // 设置拦截器,与InvocationHandler类似
        UserServiceImpl proxy = (UserServiceImpl) enhancer.create();    // 创建代理对象

        // 调用接口方法
        proxy.deleteById("1");
        // 调用类自身方法
        proxy.method1();
        // 调用父类方法
        proxy.baseMethod();
    }

    @RequiredArgsConstructor
    static class UserServiceMethodInterceptor implements MethodInterceptor {

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            this.before();

            // 使用反射调用，与jdk代理一致。可以正常调用
            // Object invoke = method.invoke(target, args);

            // 使用反射调用，对象使用代理对象。产生StackOverflowError异常。不能正常调用
            // Object invoke = method.invoke(obj, args);

            // 使用MethodProxy对象invoke方法调用，对象使用目标对象，即在目标对象上执行方法。可以正常调用
            // Object invoke = methodProxy.invoke(target, args);

            // 使用MethodProxy对象invoke方法调用，对象使用代理对象，即在代理对象上执行方法。产生StackOverflowError异常。不能正常调用
            // Object invoke = methodProxy.invoke(obj, args);

            // 使用MethodProxy对象invokeSuper方法调用，对象使用目标对象，即在目标对象上执行(CGLIB$..$..)方法。该类方法时代理类独有所以在执行时会将对象转换为代理类。产生类型转换异常。不能正常调用
            // Object invoke = methodProxy.invokeSuper(target, args);

            // 使用MethodProxy对象invokeSuper方法调用，对象使用代理对象，即在代理对象上执行(CGLIB$..$..)方法。(CGLIB$..$..)在调用父类实现。可以正常调用
            Object invoke = methodProxy.invokeSuper(obj, args);

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

    /**
     * 接口
     */
    interface UserService {
        boolean deleteById(String id);
    }

    /**
     * 父类
     */
    static class BaseService {

        boolean baseMethod() {
            System.out.println("baseMethod");
            return false;
        }
    }

    static class UserServiceImpl extends BaseService implements UserService {

        @Override
        public boolean deleteById(String id) {
            System.out.println("deleteById: " + id);
            return false;
        }

        public boolean method1() {
            System.out.println("method1");
            return false;
        }
    }
}
