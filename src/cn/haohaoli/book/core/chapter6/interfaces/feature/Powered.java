package cn.haohaoli.book.core.chapter6.interfaces.feature;

/**
 * 动力接口
 * @author LiWenHao
 * @date 2019-01-31 16:24
 */
public interface Powered extends Moveable {

    /**
     * TODO 接口中可以包含常量
     *  与接口中的方法都自动的被设置为public一样,接口中的域将自动设为`public static final`
     */
    double SPEED_LIMIT = 95;

    /**
     * TODO 接口特性
     *  接口不是类 尤其不能使用new运算符实例化一个接口
     *      x = new Powered();  //error
     *  尽管不能构造接口的对象，却能声明接口的变量
     *      Powered x;           //ok
     *  接口变量必须引用实现了接口的类对象
     *  也可以建立类的继承关系一样， 接口也可以被扩展。
     *  接口可以多继承,但是类只能单继承
     *  资料 https://www.cnblogs.com/zhangzhangY/p/7407325.html
     */
    double milesPerGallon();

    /**
     * TODO 接口和抽象类
     *  在Comparable这个接口中只有一个方法，我们是否可以将Comparable设计成abstract
     *   如下：
     *    abstract Comparable<T> {
     *        public abstract void compareTo (T other);
     *    }
     *   然后，Employee类继承这个抽象类，并提供compareTo方法的实现
     *    如下：
     *     class Employee extends Comparable<Employee>{
     *         public void compareTo(Employee other){
     *             ...
     *         }
     *     }
     *    如果这样一来问题就来了，类只支持单继承，每个类只可以拓展一个类，
     *    假如Employee类以及拓展于一个类，例如Person。他就不能再拓展其他的类
     *     class Employee extends Person,Comparable<Employee> //error
     *    但是如果使用接口就不会有问题，一个类可以实现多个接口
     *     class Employee extends Person implements Comparable<Employee> //ok
     *
     */
}
