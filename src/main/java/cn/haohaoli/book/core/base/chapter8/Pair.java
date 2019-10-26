package cn.haohaoli.book.core.base.chapter8;

import lombok.*;

import java.util.function.Supplier;

/**
 * TODO 泛型类
 *  一个泛型类就是具有一个或者多个类型变量的类
 *  Pair类中引入了一个类型变量 'T',用尖括号 '<>' 括起来,并放在类名后面
 *  泛型类可以有多个类型变量
 *      参考: {@link java.util.Map}或{@link java.util.function.BiFunction}等
 * @author LiWenHao
 * @date 2019/5/25 10:56
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Pair<T> {

    // 不能在静态中使用
    // private static T staticField;  //错误

    private T first;
    private T second;

    public Pair() {
        // this.first = new T();    //错误
        // this.second = new T();   //错误
    }

    /**
     * 使用反射创建
     * @param clazz
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> Pair<T> makePair(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return new Pair<>(clazz.newInstance(), clazz.newInstance());
    }

    /**
     * 使用Supplier(函数式接口)创建
     * @param supplier
     * @param <T>
     * @return
     */
    public static <T> Pair<T> makePair(Supplier<T> supplier) {
        return new Pair<>(supplier.get(), supplier.get());
    }
}
