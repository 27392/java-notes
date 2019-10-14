package cn.haohaoli.book.core.base.chapter8;

import java.util.Optional;

/**
 * 泛型方法
 * @author LiWenHao
 * @date 2019/10/14 16:05
 */
public class GenericMethodTest {

    public static void main(String[] args) {

        /**
         * 在调用一个泛型方法时,在方法名前的尖括号中放入具体的类型
         *  GenericMethodTest.<String>getMiddle("John", "Q.", "Public");
         *  这种情况下方法可以省略<String>,编译器有足够的信息能够推断出所调用的方法。
         *   他用的入参类型 (即 String[]) 与泛型类 T[] 进行匹配并推断出 'T' 一定是String
         */
        String middle = GenericMethodTest.getMiddle("John", "Q.", "Public");
        Optional.ofNullable(middle).ifPresent(System.out::println);
    }

    // 抑制警告
    @SafeVarargs
    private static <T> T getMiddle(T... a) {
        return a[a.length / 2];
    }
}
