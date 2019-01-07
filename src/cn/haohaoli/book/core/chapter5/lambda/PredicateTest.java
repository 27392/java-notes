package cn.haohaoli.book.core.chapter5.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author liWenHao
 * @date 2019/1/7 11:31
 */
public class PredicateTest {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Predicate<Integer> predicate = i -> i > 1;
        list.stream().filter(predicate.and(i -> i == 9).or(i -> i == 10)).collect(Collectors.toList()).forEach(System.out::println);
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
