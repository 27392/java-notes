package cn.haohaoli.book.core.chapter5.lambda;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author liWenHao
 * @date 2019/1/7 11:30
 */
public class FunctionTest {

    public static void main(String[] args) {
        Function<String, Person> function = (s) -> new Person(s);
        Function<String, String> function1 = s1 -> s1 + "bbb";
        //TODO 返回一个先执行当前函数对象apply方法再执行after函数对象apply方法的函数对象。
        Function<String, String> function2 = function1.andThen(s1 -> s1 + "--ccc");
        String after = function2.apply("aaa--");
        System.out.println(after);
        //TODO 返回一个先执行before函数对象apply方法再执行当前函数对象apply方法的函数对象
        Function<String, String> function3 = s1 -> s1 + "--ccc";
        Function<String, String> compose = function3.compose(function1);
        String before = compose.apply("aaa--");
        System.out.println(before);

        /**
         * TODO compose 和 andThen 的不同之处是函数执行的顺序不同。
         *  compose 函数先执行参数，然后执行调用者，而 andThen 先执行调用者，然后再执行参数。
         *  参考：https://blog.csdn.net/huo065000/article/details/78964382
         */
        //返回一个执行了apply()方法之后只会返回输入参数的函数对象
        Object string = Function.identity().apply("string");
        BiFunction<String, Integer, Person> biFunction = (s, a) -> new Person(s, a);
    }


}
