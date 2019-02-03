package cn.haohaoli.book.core.chapter6.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * TODO Predicate 接口
 *  参考：https://blog.csdn.net/huo065000/article/details/78964875
 *  Predicate<T> 参数 T  返回 boolean
 *   方法：
 *    主要方法：
 *      boolean test(T t);  判断条件是否成立
 *    其他方法：
 *      default and         且操作，既满足A也满足B条件
 *      default negate      取反操作 为true则返回false
 *      default or          或操作，满足A条件或者满足B条件
 *      static  isEqual     判定是否相等
 * @author liWenHao
 * @date 2019/1/7 11:31
 */
public class PredicateTest {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Predicate<Integer> predicate = i -> i > 1;
        list.stream().filter(predicate.and(i -> i == 9).or(i -> i == 10)).collect(Collectors.toList()).forEach(System.out::println);
        //isEqual
        boolean test = Predicate.isEqual("test1").negate().test("test");
        System.out.println(test);

        System.out.println("==============BiPredicate===============");
        BiPredicate<Integer, Integer> biPredicate = (i1, i2) -> i1 + i2 > 50;
        boolean test1 = biPredicate.negate().test(20, 20);
        boolean test2 = biPredicate.test(30, 25);
        System.out.println(test1);
        System.out.println(test2);
        System.out.println("==============IntPredicate===============");
        filter(list, (i) -> i > 5).forEach(System.out::println);
    }

    public static List<Integer> filter(List<Integer> list, IntPredicate predicate) {
        List<Integer> tempList = new ArrayList<>();
        for (Integer integer : list) {
            if (predicate.test(integer)) {
                tempList.add(integer);
            }
        }
        return tempList;
    }
}
