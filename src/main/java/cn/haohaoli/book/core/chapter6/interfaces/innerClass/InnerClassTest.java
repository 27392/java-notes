package cn.haohaoli.book.core.chapter6.interfaces.innerClass;

import cn.haohaoli.book.core.chapter6.interfaces.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO 内部类
 *  资料：
 *      https://www.cnblogs.com/dolphin0520/p/3811445.html
 *      https://www.cnblogs.com/chenssy/p/3388487.html   （系列）
 *      https://www.cnblogs.com/nerxious/archive/2013/01/25/2876489.html
 *  在Java中，可以将一个类定义在另一个类里面或者一个方法里面，这样的类称为内部类(inner class)。
 *  广泛意义上的内部类一般来说包括这四种：成员内部类、局部内部类、匿名内部类和静态内部类。
 *  为什么需要使用内部类呢?
 *   其主要原因有以下三点:
 *      •内部类方法可以访问该类定义所在的作用域中的数据， 包括私有的数据。
 *      •内部类可以对同一个包中的其他类隐藏起来。
 *      •当想要定义一个回调函数且不想编写大量代码时，使用匿名(anonymous) 内部类比较便捷
 * @author LiWenHao
 * @date 2019-02-04 00:32
 */
public class InnerClassTest {

    public static void main(String[] args) {
        /**
         * TODO 成员内部类是依附外部类而存在的
         *  如果要创建成员内部类的对象，前提是必须存在一个外部类的对象。创建成员内部类对象的一般方式如下：
         */
        //第一种方式
        OuterClass outerClass = new OuterClass(12d);
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();       //必须使用outerClass对象(外部类)来创建
        //第二种方式
        OuterClass.InnerClass innerClassInstance = outerClass.getInnerInstance();

        method(new Employee("明明",1));

        OuterClass.Inner s = new OuterClass.Inner("明明");
        s.displayName();

        /**
         * TODO 匿名内部类
         *  匿名内部类也就是没有名字的内部类
         *  正因为没有名字，所以匿名内部类只能使用一次，它通常用来简化代码编写
         *  但使用匿名内部类还有个前提条件：必须继承一个父类或实现一个接口
         */
        Person person = new Person() {
            @Override
            public void eat() {
                System.out.println("吃饭");
            }
        };
        System.out.println("===============匿名内部类===============");
        person.eat();

        /**
         * TODO 双括号初始化
         */
        List<Employee> employeeList = new ArrayList<Employee>(){{
            add(new Employee("x",1));
            add(new Employee("y",2));
            add(new Employee("z",3));
        }};
        System.out.println(employeeList);

    }

    interface Person{
        void eat();
    }

    /**
     * TODO 局部内部类
     *  局部内部类是定义在一个方法或者一个作用域里面的类，它和成员内部类的区别在于局部内部类的访问仅限于方法内或者该作用域内。
     *  注意! 局部内部类就像是方法里面的一个局部变量一样，是不能有public、protected、private以及static修饰符的
     */
    public static void method (Employee e) {

        class Result {
            private String name;

            public Result(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public String toString() {
                return "Result{" +
                        "name='" + name + '\'' +
                        '}';
            }
        }
        Result result = new Result(e.getName());
        System.out.println("===============局部内部类===============");
        System.out.println(result);
    }

}

class OuterClass {

    private InnerClass innerClass;
    private double radius = 0;
    public static int count = 1;

    public OuterClass(double radius) {
        this.radius = radius;
        /**
         * TODO 外部类访问成员内部类
         *  虽然成员内部类可以无条件地访问外部类的成员，而外部类想访问成员内部类的成员却不是这么随心所欲了。
         *  在外部类中如果要访问成员内部类的成员，必须先创建一个成员内部类的对象，再通过指向这个对象的引用来访问：
         */
        getInnerInstance().display();
    }

    public InnerClass getInnerInstance() {
        return innerClass == null ? new InnerClass() : innerClass;
    }

    /**
     * TODO 成员内部类
     *  类InnerClass像是类OuterClass的一个成员，OuterClass称为外部类。成员内部类可以无条件访问外部类的所有成员属性和成员方法（包括private成员和静态成员)
     *  成员内部类中不能存在任何static的变量和方法
     */
    public class InnerClass {

        private double radius = 0;

        public void display() {
            System.out.println("===============成员内部类===============");

            /**
             * TODO 成员内部类拥有与外部外部类同名变量或方法
             *  当成员内部类拥有和外部类同名的成员变量或者方法时，会发生隐藏现象，即默认情况下访问的是成员内部类的成员。
             *  如果要访问外部类的同名成员，需要以下面的形式进行访问：
             *      外部类.this.成员变量
             *      外部类.this.成员方法
             */
            System.out.println(OuterClass.this.radius);
            System.out.println(radius);              //外部类的private成员
            System.out.println(count);               //外部类的静态成员
        }
    }

    /**
     * TODO 静态内部类
     *  静态内部类也是定义在另一个类里面的类，只不过在类的前面多了一个关键字static。
     *  静态内部类是不需要依赖于外部类的，这点和类的静态成员属性有点类似，并且它不能使用外部类的非static成员变量或者方法，
     *  这点很好理解，因为在没有外部类的对象的情况下，可以创建静态内部类的对象，如果允许访问外部类的非static成员就会产生矛盾，因为外部类的非static成员必须依附于具体的对象
     */
    static class Inner {

        static double radius = 0;
        private String name;

        public Inner(String name) {
            this.name = name;
        }

        void displayName () {
            System.out.println("===============静态内部类===============");
            System.out.println(name);
            System.out.println(count);
        }

        static void display() {
            System.out.println(radius);
            //System.out.println(OuterClass.this.radius);   //error  不能访问为static变量
            System.out.println(count);                      //ok
        }
    }
}