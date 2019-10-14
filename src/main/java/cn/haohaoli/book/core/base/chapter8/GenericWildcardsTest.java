package cn.haohaoli.book.core.base.chapter8;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Optional;

/**
 * 泛型通配符
 *
 * @author LiWenHao
 * @date 2019/10/14 16:18
 */
public class GenericWildcardsTest {

    public static void main(String[] args) {
        /**
         * TODO 泛型通配符
         *  通配符类型中,允许类型参数变化.
         *    例如：Pair<? extends Employee>
         *   表示任何泛型Pair类型，它的类型参数是Employee的子类,如Pair<Manager> 但是不是Pair<String>
         *   类型 Pair<Manager> 是 Pair<? extends Employee> 的子类
         *   使用 Pair<? extends Employee> 在调用 'setFirst()' 方法时有一个类型错误
         *      实际方法其实是这样
         *          ? extends Employee getFirst()
         *          void setFirst(? extends Employee)
         *      这样将无法调用 'setFirst()' 方法.编译器只知道需要某个Employee的子类型,但不知道具体是什么类型,
         *      它拒绝传递特定的类型.毕竟 '?' 不能用来匹配。
         *      使用 'getFirst()' 方法就不存在这个问题:将 'getFirst()' 的返回值赋给一个Employee的应用完全合法
         */
        Pair<Manager> managerBuddies = new Pair<>();
        printBuddies(managerBuddies);

        Pair<? extends Employee> wildcardBuddies = managerBuddies;  //OK
        // wildcardBuddies.setFirst(new Employee());                //错误


        /**
         * TODO 泛型通配符的超类型限定
         *  通配符限定与类型变量限定十分类似,但是,还有一个附加的能力,既可以指定一个超类型限定
         *    例如 ： '? super Manager'
         *    这个通配符限制为Manager的所有的 `超类`,为什么这么做呢?带有超类限定的通配符与通配符相反
         *    可以为方法提供参数，但不能使用返回值
         *       实际的方法是这样
         *          ? super Manager getFirst()
         *          void setFirst(? super Manager)
         *       编译器无法知道 'setFirst()'方法的具体类型,因为调用者这个方法是不能接受类型为 Employee 或者 Object 的参数
         *       只能传递 Manager 类型的对象，或者某个子类(如 Executive) 对象。
         *       另外,如果调用 'getFirst()' 方法,不能保证返回对象的类型.只能把他赋给一个Object
         *          Object o = superQualifiedManager.getFirst();
         *  要点！！！：
         *    直观的讲,带有超类限定符的通配符可以向泛型对象写入。带有子类限定符通配符的可以从泛型对象读取！
         *
         */
        Manager[]      managers     = {new Manager(2.0), new Manager(1.0)};
        Pair<Employee> employeePair = new Pair<>();

        minmaxBonus(managers, employeePair);
        System.out.println(employeePair);

        /**
         * TODO 超类型限定的另外用法
         *  Manager 继承了Employee,而Employee 拓展了 Comparable<Employee>
         *  因此 Manager 实现的是 Comparable<Employee>在这种情况下 超类型可以补救
         *      private static <T extends Comparable<? super T>> T minBonus(T[] a)
         */
        Manager[] managerArray = {new Manager(2.0), new Manager(1.0)};
        Manager   manager      = minBonus(managerArray);
        System.out.println(manager);
    }

    private static void printBuddies(Pair<? extends Employee> pair) {
        Optional.ofNullable(pair).ifPresent(System.out::println);
    }

    private static void minmaxBonus(Manager[] a, Pair<? super Manager> result) {
        if (null == a || a.length == 0) {
            return;
        }
        Manager min = a[0];
        Manager max = a[0];
        for (Manager m : a) {
            if (min.compareTo(m) < 0) {
                min = m;
            }
            if (max.compareTo(m) > 0) {
                max = m;
            }
        }
        result.setFirst(max);
        result.setSecond(min);
    }

    private static <T extends Comparable<? super T>> T minBonus(T[] a) {
        if (null == a || a.length == 0) {
            return null;
        }
        T min = a[0];
        for (T t : a) {
            if (min.compareTo(t) > 0) {
                min = t;
            }
        }
        return min;
    }

    private static boolean hasNulls(Pair<?> p) {
        return p.getFirst() == null || p.getSecond() == null;
    }

    ///////////////////////////////////////////////////////////////////////////
    // class
    ///////////////////////////////////////////////////////////////////////////
    
    @Getter
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    private static abstract class Employee implements Comparable<Employee> {

        private Double bonus;

        @Override
        public int compareTo(Employee o) {
            return null == o ? 0 : Double.compare(bonus, o.getBonus());
        }
    }

    private static class Manager extends Employee {

        Manager(Double bonus) {
            super(bonus);
        }

    }
}
