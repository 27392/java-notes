package cn.haohaoli.book.core.base.chapter5.inheritance;

/**
 * TODO 阻止继承: final 类和方法
 *  可能希望阻止人们利用某个类定义子类。不允许扩展的类被称为 final 类。如果 在定义类的时候使用了 final 修饰符就表明这个类是 final 类。
 *  例如， 假设希望阻止人们定义 Executive 类的子类， 就可以在定义这个类的时候 使用 final 修饰符声明
 * @author LiWenHao
 * @date 2019-01-23 20:57
 */
public final class Executive extends Manager {


    public Executive() {
        super("新建人员", 0d, 2019, 1, 1);
    }

    public Executive(String name, double salary, int year, int month, int day) {
        super(name, salary, year, month, day);
    }
}
