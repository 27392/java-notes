package cn.haohaoli.book.core.chapter5.inheritance;

/**
 * 经理测试类
 * @author LiWenHao
 * @date 2019-01-23 20:42
 */
public class ManagerTest {

    public static void main(String[] args) {

        Manager manager = new Manager("张三", 80000, 1987, 12, 15);
        manager.setBonus(5000d);
        Employee[] staff = new Employee[3];
        staff[0] = manager;
        staff[1] = new Employee("李四", 50000, 1989, 10, 1);
        staff[2] = new Employee("王五", 40000, 1990, 3, 15);

        /**
         * TODO 多态 （polymorphism）
         *  调用能够确定应该执行哪个 getSalary 方法。
         *   请注意
         *      尽管这里将 e 声明为 Employee 类型， 但实际上 e 既可以引用 Employee 类型的对象， 也可以引用 Manager 类型的对象。
         *      当 e 引用 Employee 对象时，e.getSalary( ) 调用的是 Employee 类中的 getSalary 方法;
         *      当 e 引用 Manager 对象时，e.getSalary( ) 调用的是 Manager 类中的 getSalary 方法。
         *      虚拟机知道 e 实际引用的对象类型， 因此能够正确地调用相应的方法。
         *      一个对象变量(例如， 变量 e) 可以指示多种实际类型的现象被称为多态(polymorphism)。
         *      在运行时能够自动地选择调用哪个方法的现象称为动态绑定(dynamic binding);
         *  这里的 staff[1] 和 staff[2] 仅输出了基本薪水， 这是因为它们对应的是 Employee 对象，
         *  而 staff[0] 对应的是 Manager 对象，它的 getSalary 方法将奖金与基本薪水加在了一起。
         *      张三 - 85000.0
         *      李四 - 50000.0
         *      王五 - 40000.0
         */
        for (Employee e : staff) {
            System.out.println(e.getName() + " - " + e.getSalary());
        }

        /**
         * 在 Java 程序设计语言中， 对象变量是多态的。
         * 一个 Employee 变量既可以引用一个 Employee 类对象， 也可以引用一个 Employee 类的任何一个子类的对象 (例如， Manager、Executive、Secretary 等)
         */
        Employee e;
        e = new Employee("e",1993,1993,3,15);
        e = new Manager("m",1,1991,12,25);

        //注意
        Manager[] managers = new Manager[10];
        staff = managers;
        /**
         * managers数组转换成staff数组是完全没问题
         * 此时 staff 和 managers 引用同一个数组
         *  staff[0] = new Employee("李四", 50000, 1989, 10, 1);
         *  等同于 staff[0] 和 managers[0] 是同一个对象
         *  managers[0].setBonus(99999d);
         *      错误因为在数组0的位置是一个职员对象 并没有Bonus字段
         *      这时就会抛出异常 ArrayStoreException
         */

        Employee[] staff1 = new Employee[2];
        staff1[0] = new Employee("e",1993,1993,3,15);
        staff1[0] = new Manager("m",1,1991,12,25);

        /**
         * TODO 强制类型转换
         *  将一个值存人变量时， 编译器将检查是否允许该操作。将一个子类的引用赋给一个超类变量，编译器是允许的。
         *  但将一个超类的引用赋给一个子类变量， 必须进行类型转换， 这样 才能够通过运行时的检査
         *  如果试图在继承链上进行向下的类型转换 java 运行时系统将报告这个错误， 并产生一个 ClassCastException 异常。 如果没有捕获这个异常， 那么程序就会终止。
         *  在进行类型转换之前， 先查看一下是否能够成功地转换。这个过程简单地使用 instanceof 操作符就可以实现
         *    综上所述:
         *      •只能在继承层次内进行类型转换。
         *      •在将超类转换成子类之前， 应该使用 instanceof 进行检查。
         * Manager m = (Manager) staff[0];  //error
         */
        //使用instanceof 检查
        if (staff[0] instanceof Manager) {
            Manager m = (Manager) staff[0];
        }

    }
}
