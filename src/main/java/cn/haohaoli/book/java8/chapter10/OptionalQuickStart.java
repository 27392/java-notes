package cn.haohaoli.book.java8.chapter10;

import java.util.Optional;

/**
 * Optional类介绍:
 * `Optional<T>`对象是一种包装器对象,要么包装了类型`T`的对象,要么没有包装任何对象
 * 对于第一种情况,我们称这种值为存在的
 * `Optional<T>`类型被当做一种更安全的方式,用来替代`T`的引用,这种引用要么引用某个对象,要么为`null`
 *
 * @author LiWenHao
 */
public class OptionalQuickStart {

    public static void main(String[] args) {

        // 创建`Optional`实例
        create();

    }

    /*
        创建`Optional`对象

        1. 声明一个空的`Optional`,可以通过`Optional.empty()`静态方法来创建

        2. 依据一个非空值创建`Optional`,使用`Optional.of(T value)`静态方法
            如果参数是`null`,这段代码会抛出一个`NullPointerException`

        3. 可接受`null`的`Optional`,使用`Optional.ofNullable()`你可以创建一个允许`null`值的`Optional`对象
     */
    private static void create() {
        Optional<String> empty    = Optional.empty();
        Optional<String> optional = Optional.of("hello world");
        Optional<String> nullAble = Optional.ofNullable(null);

        System.out.println(empty);
        System.out.println(optional);
        System.out.println(nullAble);

    }
}
