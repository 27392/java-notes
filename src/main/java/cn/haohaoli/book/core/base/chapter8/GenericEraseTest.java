package cn.haohaoli.book.core.base.chapter8;

import lombok.Getter;

/**
 * 泛型擦除
 * @author LiWenHao
 * @date 2019/10/15 18:34
 */
public class GenericEraseTest {

    public static void main(String[] args) {

        Pair<String>  stringPair  = new Pair<>();
        Pair<Integer> integerPair = new Pair<>();
        System.out.println(stringPair.getClass() == integerPair.getClass());
        // 结果为:true,说明泛型已经被擦除

        Limit      limit = new Limit();
        Comparable value = limit.getT();
        // 使用第一个限定的类型变量来替换

        Pair<Integer> pair  = new Pair<>();
        Integer       first = pair.getFirst();
        // 如果擦除返回类型,编译器将会插入强制类型转换
        // Integer first = (Integer)pair.getFirst();
    }

    ///////////////////////////////////////////////////////////////////////////
    // class
    ///////////////////////////////////////////////////////////////////////////

    @Getter
    private static class Limit<T extends Comparable & Cloneable> {

        private T t;

        public int compareTo(T o) {
            return o.compareTo(this.t);
        }
    }

    @Getter
    private static class Limi2<T extends Cloneable & Comparable> {

        private T t;

        public int compareTo(T o) {
            // 如果在调用第二个限定类的方法时就会进行强制类型转换
            // return ((Comparable)o).compareTo(this.t);
            return o.compareTo(this.t);
        }
    }

}

