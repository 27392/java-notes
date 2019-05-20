package cn.haohaoli.book.core.base.chapter6.lambda;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * TODO Function 接口
 *  参考：https://blog.csdn.net/huo065000/article/details/78964382
 *  Function<T,R>   参数 T  返回 R
 *    方法：
 *     主要方法：
 *      R apply(T t);       将Function对象应用到输入的参数上，然后返回计算结果。
 *     其他方法：
 *      default compose     返回一个先执行before函数对象apply方法再执行当前函数对象apply方法的函数对象
 *      default andThen     返回一个先执行当前函数对象apply方法再执行after函数对象apply方法的函数对象。
 *      static  identity    返回一个执行了apply()方法之后只会返回输入参数的函数对象
 *
 * @author liWenHao
 * @date 2019/1/7 11:30
 */
public class FunctionTest {

    public static void main(String[] args) {

        /**
         * TODO 构造器引用
         *  构造器引用与方法引用很类似，只不过方法名为 new
         *  例如， Person::new 是 Person 构造器的一个引用。哪一个构造器呢? 这取决于上下文
         */
        Function<String, Person> function = Person::new;
        Person person1 = function.apply("小红");
        System.out.println(person1);
        BiFunction<String, Integer, Person> biFunction = Person::new;
        Person person2 = biFunction.apply("小明", 12);
        System.out.println(person2);

        //andThen
        Function<String, String> function1 = s1 -> s1 + "bbb";
        Function<String, String> function2 = function1.andThen(s1 -> s1 + "--ccc");
        String after = function2.apply("aaa--");
        System.out.println("andThen： " + after);

        //compose
        Function<String, String> function3 = s1 -> s1 + "--ccc";
        Function<String, String> compose = function3.compose(function1);
        String before = compose.apply("aaa--");
        System.out.println("compose： " + before);

        //identity
        Object string = Function.identity().apply("string");
        System.out.println(string);

        //UnaryOperator 一元运算
        UnaryOperator<String> unaryOperator = (s) -> "xxxx" + s;
        String str = unaryOperator.apply("sss");
        System.out.println(str);

        //BinaryOperator 二元运算
        BinaryOperator<String> binaryOperator = BinaryOperator.maxBy((Comparator.comparingInt(String::length)));
        String apply = binaryOperator.apply("xx", "xxxx");
        System.out.println("BinaryOperator.maxBy 最长的是： " + apply);

        BinaryOperator<Integer> operator = BinaryOperator.minBy(Comparator.comparingInt(o -> o));
        Integer apply1 = operator.apply(1, 2);
        System.out.println("BinaryOperator.minBy 最小的是： " + apply1);

    }


}
