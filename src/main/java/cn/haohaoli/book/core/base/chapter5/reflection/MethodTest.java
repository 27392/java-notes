package cn.haohaoli.book.core.base.chapter5.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author LiWenHao
 */
public class MethodTest {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        System.out.println("===========getMethods===========");
        getMethods(Example.class);

        System.out.println("===========invoke===========");
        invoke();
    }

    /**
     * 获取方法
     * @param clazz
     */
    private static void getMethods(Class<?> clazz) {

        // 获取该类对象的所有公共方法,包括类或接口声明的方法以及从超类和超接口继承的方法
        Method[] methods = clazz.getMethods();
        // 获取该类对象所表示的类或接口的所有声明方法,包括public、protected、default (package)访问和private方法(但不包括继承的方法)
        Method[] declaredMethods = clazz.getDeclaredMethods();

        System.out.println("getMethods(): ");
        printInfo(methods);

        System.out.println("\ngetDeclaredMethods(): ");
        printInfo(declaredMethods);
    }

    /**
     * 调用方法
     *
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    private static void invoke() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Example example = Example.class.newInstance();

        Method method = Example.class.getMethod("method2", String.class);
        Object invoke = method.invoke(example, "x");
        System.out.println("return: " + invoke);

        Method method1 = Example.class.getMethod("method1");
        Object invoke3 = method1.invoke(example, null);     // 没有参数传`null`
        System.out.println("return: " + invoke3);

        Method method2 = Example.class.getDeclaredMethod("method2", String.class, String.class);
        method2.setAccessible(true);
        Object invoke1 = method2.invoke(example, "x", "y");
        System.out.println("return: " + invoke1);

        // 调用静态方法
        Method method3 = Example.class.getDeclaredMethod("method3", String.class);
        Object invoke2 = method3.invoke(null, "static");    // 第一个参数可以省略
        System.out.println(invoke2);

    }

    private static void printInfo(Method[] declaredMethods) {
        for (Method declaredMethod : declaredMethods) {
            System.out.print(Modifier.toString(declaredMethod.getModifiers()));
            System.out.print(" " + declaredMethod.getReturnType().getSimpleName());
            System.out.print(" " + declaredMethod.getName() + " (");
            Class<?>[] parameterTypes = declaredMethod.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                System.out.print(parameterTypes[i].getSimpleName());
                if (i != parameterTypes.length - 1) {
                    System.out.print(" ,");
                }
            }
            System.out.print(")\n");
        }
    }

    static class Example extends AbstractExample {

        public String method2(String a) {
            System.out.println("method2: " + a);
            return a;
        }

        private void method2(String a, String b) {
            System.out.println("method2: " + a + " : " + b);
        }

        public static void method3(String a) {
            System.out.println("method3: " + a);
        }
    }

    static abstract class AbstractExample {

        public void method1() {
            System.out.println("method1");
        }

        private String method1(String a) {
            System.out.println("method1 : " + a);
            return a;
        }
    }
}
