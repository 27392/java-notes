package cn.haohaoli.book.core.chapter6.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * TODO Lambda
 * @author liWenHao
 * @date 2019/1/5 16:43
 */
public class LambdaTest {

    public static void main(String[] args) {
        /**
         * TODO 方法引用(method reference)
         *  将已经有现成的方法可以完成你想要传递到其他代码的某个动作
         *   表达式 System.out::println 是一个方法引用, 它等价于 lambda 表达式 x 一> System.out.println(x)
         *   要用:: 操作符分隔方法名与对象或类名。主要有 3 种情况:
         *      • object::instanceMethod
         *      • Class ::static Method
         *      • Class ::instanceMethod
         *      在前2种情况中， 方法引用等价于提供方法参数的 lambda 表达式。
         *          前面已经提到， System.out::println 等价于 x -> System.out.println(x) 类似地，Math::pow 等价于(x，y) -> Math.pow(x, y)
         *      对于第3种情况， 第1个参数会成为方法的目标。
         *          例如， String::compareToIgnoreCase 等 同于 (x, y) -> x.compareToIgnoreCase(y)
         */

        String[] s = {"xxx", "sss", "ff", "ggggg"};
        Arrays.sort(s, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(s));

        List<String> strings = Arrays.asList(s);
        Stream<Person> personStream = strings.stream().map(Person::new);
        Person[] people = personStream.toArray(Person[]::new);
        Arrays.stream(people).forEach(System.out::println);
        repeat(10, () -> System.out.println("Hello World"));
    }

    public static void repeat(int i, Runnable runnable) {
        for (int j = 0; j < i; j++) {
            runnable.run();
        }
    }

}
