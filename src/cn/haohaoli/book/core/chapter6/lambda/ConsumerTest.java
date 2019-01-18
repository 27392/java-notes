package cn.haohaoli.book.core.chapter6.lambda;

import cn.haohaoli.book.core.chapter6.lambda.factory.Tiger;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.Predicate;

/**
 * @author liWenHao
 * @date 2019/1/7 11:22
 */
public class ConsumerTest {

    //Consumer<T>     T        处理一个T类型的值
    public static void main(String[] args) {
        repeat(10, System.out::println);

        Tiger animal = new Tiger();
        animal.setName("小白");

        changeColor(animal, tiger -> tiger.getName().equals("小白"), System.out::println);

    }

    public static void repeat(int i, IntConsumer intConsumer) {
        for (int j = 0; j < i; j++) {
            intConsumer.accept(j);
        }
    }

    public static Tiger changeColor(Tiger t, Predicate<Tiger> predicate, Consumer<Tiger> consumer) {
        if (predicate.test(t)) {
            consumer.accept(t);
        }
        return t;
    }

}
