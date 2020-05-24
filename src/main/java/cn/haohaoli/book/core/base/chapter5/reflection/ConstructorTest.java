package cn.haohaoli.book.core.base.chapter5.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.StringJoiner;

/**
 * @author LiWenHao
 */
public class ConstructorTest {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {

        System.out.println("==========getConstructors==========");
        getConstructors(Example.class);

        System.out.println("==========newInstance==========");
        newInstance(Example.class);
    }

    /**
     * 获取构造器对象
     *
     * @param clazz
     */
    private static void getConstructors(Class<?> clazz) {
        Constructor<?>[] constructors         = clazz.getConstructors();
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();

        System.out.println("getConstructors() length: " + constructors.length);
        System.out.println("getDeclaredConstructors() length: " + declaredConstructors.length);

        System.out.println("\ngetConstructors() forEach");
        for (Constructor<?> constructor : constructors) {
            System.out.print(Modifier.toString(constructor.getModifiers()) + " " + clazz.getSimpleName() + " (");
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                System.out.print(parameterTypes[i].getSimpleName());
                if (i != parameterTypes.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print(")\n");
        }

        System.out.println("\ngetDeclaredConstructors() forEach");
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.print(Modifier.toString(declaredConstructor.getModifiers()) + " " + clazz.getSimpleName() + " (");
            Class<?>[] parameterTypes = declaredConstructor.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                System.out.print(parameterTypes[i].getSimpleName());
                if (i != parameterTypes.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.print(")\n");
        }
    }

    /**
     * 通过反射创建实例
     *
     * @param clazz
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    private static void newInstance(Class<?> clazz) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {

        Constructor<?> constructor = clazz.getConstructor(Integer.class, String.class);
        Object         o           = constructor.newInstance(1, "赵四");
        System.out.println(o);

        Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);    // 私有需要设置禁止Java语言访问检查
        Object o1 = declaredConstructor.newInstance();
        System.out.println(o1);
    }

    static class Example {

        Integer id;
        String  name;

        private Example() {
            System.out.println("调用私有无参构造");
        }

        public Example(Integer id, String name) {
            this.id = id;
            this.name = name;
            System.out.println("调用有参构造器");
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Example.class.getSimpleName() + "[", "]")
                    .add("id=" + id)
                    .add("name='" + name + "'")
                    .toString();
        }
    }
}
