package cn.haohaoli.book.core.base.chapter8;

import java.util.Optional;

/**
 * 泛型限定
 * @author LiWenHao
 * @date 2019/10/14 16:08
 */
public class GenericLimitTest {

    public static void main(String[] args) {

        String[] words = {"Mary", "had", "a", "little", "lamb"};

        Pair<String> mm = minmax(words);
        Optional.ofNullable(mm).ifPresent(System.out::println);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable> Pair<T> minmax(T[] ts){
        if (null == ts || ts.length == 0) {
            return null;
        }
        T min = ts[0];
        T max = ts[0];
        for (T t : ts) {
            if (max.compareTo(t) > 0) {
                max = t;
            }
            if (min.compareTo(t) < 0) {
                min = t;
            }
        }
        return new Pair<>(min, max);
    }

}
