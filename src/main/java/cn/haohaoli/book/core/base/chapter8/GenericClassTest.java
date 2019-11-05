package cn.haohaoli.book.core.base.chapter8;

import java.util.Optional;

/**
 * 泛型类
 * @author LiWenHao
 * @date 2019/10/14 15:57
 */
public class GenericClassTest {

    public static void main(String[] args) {

        // JDK7之后的版本在构造函数中可以省略泛型类,省略的类型可以从变量的类型推断得出
        Pair<String> jdk7 = new Pair<String>();
        Pair<String> jdk8 = new Pair<>();

        // 不使用泛型构造对象时,默认变量就是Object
        Pair pair = new Pair();

        String[]     words = {"Mary", "had", "a", "little", "lamb"};
        Pair<String> mm    = minmax(words);
        Optional.ofNullable(mm).ifPresent(System.out::println);
    }

    private static Pair<String> minmax(String[] ts) {
        if (null == ts || ts.length == 0) {
            return null;
        }
        String min = ts[0];
        String max = ts[0];
        for (int i = 1; i < ts.length; i++) {
            if (max.compareTo(ts[i]) > 0) {
                max = ts[i];
            }
            if (min.compareTo(ts[i]) < 0) {
                min = ts[i];
            }
        }
        return new Pair<>(min, max);
    }
}
