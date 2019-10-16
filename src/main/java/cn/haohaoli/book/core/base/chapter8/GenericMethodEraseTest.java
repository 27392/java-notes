package cn.haohaoli.book.core.base.chapter8;

import java.util.Optional;

/**
 * 泛型方法擦除
 *
 * @author LiWenHao
 * @date 2019/10/16 11:13
 */
public class GenericMethodEraseTest {

    public static void main(String[] args) {

        Comparable min = min(new String[]{"c", "a", "e", "d"});
        Optional.ofNullable(min).ifPresent(System.out::println);
        // 方法也存在泛型擦除,`<T extends Comparable>`将变成`Comparable`

        StringPair   stringPair = new StringPair();
        Pair<String> pair       = stringPair;
        pair.setSecond("xxxxx");
    }

    @SuppressWarnings("unchecked")
    private static <T extends Comparable> T min(T[] ts) {
        T min = ts[0];
        for (T t : ts) {
            if (t.compareTo(min) < 0) {
                min = t;
            }
        }
        return min;
    }

    ///////////////////////////////////////////////////////////////////////////
    // class
    ///////////////////////////////////////////////////////////////////////////

    private static class StringPair extends Pair<String> {

        @Override
        public void setSecond(String second) {
            super.setSecond(second);
        }

        /*
        桥方法,由编译器生成,而且桥方法内部其实调用的是子类字节setSecond(String)方法
        public void setSecond(Object second) {
            super.setSecond((String)second));
        }
        */
    }
}
