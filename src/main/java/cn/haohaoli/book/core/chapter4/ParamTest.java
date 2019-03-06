package cn.haohaoli.book.core.chapter4;

import java.util.Arrays;

/**
 * 参数
 * @author liWenHao
 * @date 2019/1/23 05:05
 */
public class ParamTest {

    public static void main(String[] args) {

        /**
         * TODO 值调用
         *  资料：https://www.cnblogs.com/xiaoxiaoyihan/p/4883770.html
         *  Java 程序设计语言总是采用按值调用。
         *  也就是说， 方法得到的是所有参数值的一个拷贝， 特别是，方法不能修改传递给它的任何参数变量的内容。
         *    Java 中方法参数的使用情况:
         *      •一个方法不能修改一个基本数据类型的参数 (即数值型或布尔型)。
         *      •一个方法可以改变一个对象参数的状态。
         *      •一个方法不能让对象参数引用一个新的对象。
         */

        /**
         * TODO 方法参数是对象的时候可以方法可以修改对象内的值
         *  因为方法参数得到的是对象引用的拷贝,对象拷贝和其他的拷贝同时引用同一个对象
         *    具体的执行过程为:
         *      1 ) X 被初始化为 harry 值的拷贝， 这里是一个对象的引用。
         *      2 ) raiseSalary 方法应用于这个对象引用。 x 和 harry 同时引用的那个 Employee 对象的薪金提高了 200%。
         *      3 ) 方法结束后， 参数变量 x 不再使用。 当然， 对象变量 harry 继续引用那个薪金增至 3倍的雇员对象
         */
        Employee employee = new Employee("张三",10000d);
        OverLoadingTest.tripleValue(employee);
        System.out.println(employee.getSalary());

        Employee x = new Employee("王五",10300d);
        Employee y = new Employee("赵四",4050d);
        swap(x, y);
        /**
         * 方法并没有改变存储在变量 a 和 b 中的对象引用。
         *  swap 方法的参数 x 和 y 被初始 化为两个对象引用的拷贝，
         *  这个方法交换的是这两个拷贝。
         *  在方法结束时参数变量 X 和 y 被丢弃了。
         *  原来的变量 a 和 b 仍然引用 这个方法调用之前所引用的对象
         */
        System.out.printf("name=%s,salary=%.1f\n", x.getName(), x.getSalary());
        System.out.printf("name=%s,salary=%.1f\n", y.getName(), y.getSalary());



        //可以修改数组
        String[] strArray = {"1","2"};
        OverLoadingTest.tripleValue(strArray);
        System.out.println(Arrays.toString(strArray));  //{1,x}

        /**
         * TODO 方法不能修改一个基本类型参数的值
         */
        double salary = 100d;
        OverLoadingTest.tripleValue(salary);
        System.out.println(salary);         //100
        Double salary1 = 100d;
        OverLoadingTest.tripleValue(salary1);
        System.out.println(salary1);        //100
        String str = "a";
        OverLoadingTest.tripleValue(str);
        System.out.println(str);            //a
    }

    /**
     * 对象交换
     * @param x
     * @param y
     */
    public static void swap (Employee x, Employee y) {
        Employee temp = x;
        x = y;
        y = temp;
    }
}
