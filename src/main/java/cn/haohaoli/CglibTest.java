package cn.haohaoli;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author lwh
 */
public class CglibTest {

    public static void main(String[] args) {

        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, CglibTest.class.getClassLoader().getResource("").getPath());
        UserService service = new UserServiceImpl();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println(method.getName() + ": before");

                // 正常
//                Object invoke = method.invoke(service, objects);

                // 递归
//                Object invoke = method.invoke(o, objects);

                // 正常
//                Object invoke = methodProxy.invoke(service, objects);

                // 递归
//                Object invoke = methodProxy.invoke(o, objects);

                // 类型转换异常
//                Object invoke = methodProxy.invokeSuper(service, objects);

                // 正常
//                Object invoke = methodProxy.invokeSuper(o, objects);


                System.out.println(method.getName() + ": after");
                return null;
            }
        });
        UserService proxy = (UserService) enhancer.create();
        boolean     bol   = proxy.deleteById("1");
        boolean     test  = ((BaseService) proxy).test();
        ((UserServiceImpl)proxy).haha();
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
