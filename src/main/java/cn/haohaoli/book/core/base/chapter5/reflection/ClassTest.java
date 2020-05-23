package cn.haohaoli.book.core.base.chapter5.reflection;

import java.util.Random;

/**
 * @author LiWenHao
 */
public class ClassTest {

    public static void main(String[] args) throws ClassNotFoundException {

        acquireClass();

    }

    /**
     * 获得Class对象
     * 可以三种方法获得
     *
     * @throws ClassNotFoundException
     */
    private static void acquireClass() throws ClassNotFoundException {

        // 通过对象获得Class对象
        Random                  generator = new Random();
        Class<? extends Random> aClass    = generator.getClass();

        // 直接获取某一类型的Class
        Class<Random> clazz = Random.class;

        // 通过类名称获取
        Class<?> nameClazz = Class.forName("java.util.Random");
    }
}
