package cn.haohaoli;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 * @author lwh
 */
public class JdkProxyTest {


    public static void main(String[] args) {

        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        UserService service = new UserServiceImpl();

        UserService proxy = (UserService) Proxy.newProxyInstance(JdkProxyTest.class.getClassLoader(), new Class[]{UserService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before");

                // 调用目标类的方法
                Object invoke = method.invoke(service, args);

                System.out.println("after");
                return invoke;
            }
        });

        boolean bol = proxy.deleteById("1");
    }

    interface UserService {
        boolean deleteById(String id);
    }

    static class BaseService {

        boolean test() {

            return false;
        }
    }

    static class UserServiceImpl extends BaseService implements UserService {

        @Override
        public boolean deleteById(String id) {
            System.out.println("deleteById: " + id);
            return false;
        }

        public void haha() {

        }
    }
}
