package cn.haohaoli.book.core.chapter6.lambda;

import cn.haohaoli.book.core.chapter6.lambda.factory.Animal;
import cn.haohaoli.book.core.chapter6.lambda.factory.Tiger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * TODO Consumer 接口
 *  参考：https://www.jianshu.com/p/0b955173045e
 *  Consumer<T> 参数 T  无返回值
 *   方法：
 *    主要方法：
 *      void accept(T t)        对给定的参数T执行定义的操作
 *    其他方法：
 *      default andThen         对给定的参数T执行定义的操作执行再继续执行after定义的操作
 * @author liWenHao
 * @date 2019/1/7 11:22
 */
public class ConsumerTest {

    public static void main(String[] args) {
        repeat(10, System.out::println);
        Tiger tiger = new Tiger("白虎");
        change(tiger, t -> t.getName().equals("白虎"), t -> t.setColor("白色"));
        System.out.println(tiger);

        //andThen
        Consumer<Tiger> tigerConsumer = t -> t.setName("大白虎");
        tigerConsumer = tigerConsumer.andThen(t -> t.setColor("棕白色"));
        change(tiger, t -> t.getName().equals("白虎"), tigerConsumer);
        System.out.println(tiger);

        //找到李四的集合
        List<Person> lisiList = new ArrayList<>();
        Consumer <Person> consumer  =  x -> {
            if (x.getName().equals("lisi")){
                lisiList.add(x);
            }
        };
        consumer = consumer.andThen(
            x -> lisiList.removeIf(y -> y.getAge() < 23)
        );

        Stream.of(
                new Person("zhangsan",12),
                new Person("lisi",12),
                new Person("wangwu",34),
                new Person("wangwu",12),
                new Person("lisi",12),
                new Person("lisi",42),
                new Person("zhangsan",12)
        ).forEach(consumer);
        /**
         * forEach 接口入参就是 Consumer
         * void forEach(Consumer<? super T> action);
         */
        System.out.println(lisiList);
    }

    public static void repeat(int i, IntConsumer intConsumer) {
        for (int j = 0; j < i; j++) {
            intConsumer.accept(j);
        }
    }

    /**
     * 根据条件改变老虎形态
     * @param t             老虎
     * @param predicate     条件
     * @param consumer
     */
    public static void change (Tiger t, Predicate<Tiger> predicate, Consumer<Tiger> consumer) {
        if (predicate.test(t)) {
            consumer.accept(t);
        }
    }

}
