package cn.haohaoli.book.core.base.chapter5.reflection;

import java.util.Random;

/**
 * 参考资料: https://segmentfault.com/a/1190000015416840
 * @author LiWenHao
 */
public class ClassTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        acquireClass();

        System.out.println("===========newInstance===========");
        newInstance();

        System.out.println("===========isInstance===========");
        isInstance(new Random());
        isInstance(new Object());

        System.out.println("===========isAssignableFrom===========");
        isAssignableFrom(CharSequence.class, String.class);
        isAssignableFrom(String.class, CharSequence.class);

    }

    /**
     * 获得Class对象
     * 可以三种方法获得
     *
     * @throws ClassNotFoundException
     */
    private static void acquireClass() throws ClassNotFoundException {

        // 1.通过对象获得Class对象
        Random                  generator = new Random();
        Class<? extends Random> aClass    = generator.getClass();
        System.out.println(aClass.getName());

        // 2.直接获取某一类型的Class
        Class<Random> clazz = Random.class;
        System.out.println(clazz.getName());

        // 3.通过类的全路径名称获取
        Class<?> nameClazz = Class.forName("java.util.Random");
        System.out.println(nameClazz.getName());

        System.out.println(aClass == clazz);
        System.out.println(clazz == nameClazz);
    }

    /**
     * 通过class对象获取实例(调用无参构造器)
     *
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static void newInstance() throws IllegalAccessException, InstantiationException {

        Class<Random> clazz  = Random.class;
        Random        random = clazz.newInstance();
        System.out.println(random.nextInt());
    }

    /**
     * 判断一个对象是否是调用这个方法的类或接口的实例,或是其子类的实例(等价于`instanceof`)
     *
     * @param o
     */
    public static void isInstance(Object o) {
        System.out.println(Random.class.isInstance(o));
        System.out.println(o instanceof Random);
    }

    /**
     * 判断一个类是否是与参数传入的类或接口是否相同,或者是其父类
     * @param parentClass
     * @param childClass
     */
    public static void isAssignableFrom(Class<?> parentClass, Class<?> childClass) {
        System.out.println(parentClass.isAssignableFrom(childClass));
    }

}
