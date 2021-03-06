package cn.haohaoli.book.core.base.chapter8;

import lombok.*;

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
@NoArgsConstructor
@AllArgsConstructor
public class Pair<T> {

    private T first;
    private T second;

}
