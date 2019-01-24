package cn.haohaoli.book.core.chapter5.abstractClasses;

/**
 * TODO 抽象类（关键字 abstract）
 *  为了提高程序的清晰度， 包含一个或多个抽象方法的类本身必须被声明为抽象的。
 *  如果自下而上在类的继承层次结构中上移， 位于上层的类更具有通用性， 甚至可能更加 抽象。从某种角度看， 祖先类更加通用，
 *  人们只将它作为派生其他类的基类， 而不作为想使 用的特定的实例类。
 *  例如， 考虑一下对 Employee 类层次的扩展。 一名雇员是一个人， 一名 学生也是一个人
 *     为什么要花费精力进行这样高层次的抽象呢? 每个人都有一些诸如姓名这样的属性。学生
 *     与雇员都有姓名属性， 因此可以将 getName 方法放置在位于继承关系较高层次的通用超类中。
 *     再增加一个 getDescription 方法， 它可以返回对一个人的简短描述。
 * 人类
 * @author LiWenHao
 * @date 2019-01-23 21:36
 */
public abstract class Person {

    private String name;

    Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    //使用 abstract 键字， 这样就完全不需要实现这个方法了。
    public abstract String getDescription();

}
