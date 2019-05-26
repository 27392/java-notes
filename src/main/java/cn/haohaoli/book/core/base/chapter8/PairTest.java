package cn.haohaoli.book.core.base.chapter8;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 泛型测试类
 * @author LiWenHao
 * @date 2019/5/25 12:54
 */
public class PairTest {

    public static void main(String[] args) {

        //TODO JDK7之后的版本在构造函数中可以省略泛型类,省略的类型可以从变量的类型推断得出
        Pair<String> jdk7 = new Pair<String>();
        Pair<String> jdk8 = new Pair<>();

        String[] words = {"Mary", "had", "a", "little", "lamb"};

        Pair<String> mm = ArrayAlg.minmax(words);
        Optional.ofNullable(mm).ifPresent(System.out::println);

        /**
         * 在调用一个泛型方法是，在方法名前的尖括号中放入具体的类型
         *  ArrayAlg.<String>getMiddle("John", "Q.", "Public");
         *  这种情况下方法可以省略<String>,编译器有足够的信息能够推断出所调用的方法。
         *   他用的入参类型 (即 String[]) 与泛型类 T[] 进行匹配并推断出 'T' 一定是String
         */
        String middle = ArrayAlg.getMiddle("John", "Q.", "Public");
        System.out.println(middle);

        String min = ArrayAlg.min(new String[]{"b", "a", "c"});
        System.out.println(min);

        /**
         * TODO 泛型类的继承规则
         *  Manager 继承 Employee
         *  那么 Pair<Manager> 是 Pair<Employee> 的子类吗?
         *    答案是：'不是'
         *    如果是的话将 Pair<Manager> 赋值给 Pair<Employee> 对象将不会有错误，很显然这样是有错误 所以是不是
         *  泛型可以拓展其他泛型类 {@link List<E>} {@link ArrayList<E>}
         *  ArrayList 继承 List 这就意味着一个 ArrayList<Manager> 可以被转换为一个 List<Manager>
         *  但是一个 ArrayList<Manager> 不能一个 List<Employee> 或 ArrayList<Employee>
         */
        Pair<Manager> managerPair = new Pair<>(new Manager(33d), new Manager(44d));
        // Pair<Employee> employeePairs = managerPair; // 错误
        List<Manager> managerList = new ArrayList<>();
        // List<Employee> employeeList = managerList;  //错误

        /**
         * TODO 泛型通配符
         *  通配符类型中, 允许类型参数变化。
         *    例如：Pair<? extends Employee>
         *   表示任何泛型Pair类型，它的类型参数是Employee的子类，如Pair<Manager> 但是不是Pair<String>
         *   类型 Pair<Manager> 是 Pair<? extends Employee> 的子类
         *   使用 Pair<? extends Employee> 在调用 'setFirst()' 方法时有一个类型错误
         *      实际方法其实是这样
         *          ? extends Employee getFirst()
         *          void setFirst(? extends Employee)
         *      这样将无法调用 'setFirst()' 方法。编译器只知道需要某个Employee的子类型,但不知道具体是什么类型，
         *      它拒绝传递特定的类型。毕竟 '?' 不能用来匹配。
         *      使用 'getFirst()' 方法就不存在这个问题 : 将 'getFirst()' 的返回值赋给一个Employee的应用完全合法
         */
        Pair<Manager> managerBuddies = new Pair<>();
        Pair<? extends Employee> wildcardBuddies = managerBuddies;  //OK
        // wildcardBuddies.setFirst(new Employee()); //错误
        Employee first = wildcardBuddies.getFirst();
        Pair<Employee> buddies = new Pair<>();

        printBuddies(managerPair);
        printBuddies(buddies);

        /**
         * TODO 泛型通配符的超类型限定
         *  通配符限定与类型变量限定十分类似，但是，还有一个附加的能力，既可以指定一个超类型限定
         *    例如 ： '? super Manager'
         *    这个通配符限制为Manager的所有的 `超类` ，为什么这么做呢? 带有超类限定的通配符与通配符相反
         *    可以为方法提供参数，但不能使用返回值
         *       实际的方法是这样
         *          ? super Manager getFirst()
         *          void setFirst(? super Manager)
         *       编译器无法知道 'setFirst()' 方法的具体类型，因为调用者这个方法是不能接受类型为 Employee 或者 Object 的参数
         *       只能传递 Manager 类型的对象，或者某个子类(如 Executive) 对象。
         *       另外，如果调用 'getFirst()' 方法，不能保证返回对象的类型。只能把他赋给一个Object
         *          Object o = superQualifiedManager.getFirst();
         *  要点！！！：
         *    直观的讲,带有超类限定符的通配符可以向泛型对象写入。带有子类限定符通配符的可以从泛型对象读取！
         *
         */
        Pair<? super Manager> superQualifiedManager = new Pair<>();
        superQualifiedManager.setFirst(new Manager(1d));    //OK
        superQualifiedManager.setFirst(new Executive());          //OK
        // superQualifiedManager.setFirst(new Employee(1d));      //错误
        Object o = superQualifiedManager.getFirst();


        Manager[] managers = {new Manager(2.0), new Manager(1.0)};
        Pair<Employee> employeePair = new Pair<>();
        minmaxBonus(managers, employeePair);

        Pair<Manager> managerPair1 = new Pair<>();
        minmaxBonus(managers, managerPair1);

        /**
         * Manager 继承了Employee ，而Employee 拓展了 Comparable<Employee>
         * 因此 Manager 实现的是 Comparable<Employee>
         * 在这种情况下 超类型可以补救
         *   private static <T extends Comparable<? super T>> T minBonus(T[] a)
         */
        Manager manager = minBonus(managers);
        System.out.println(manager);

        /**
         * TODO 无限定通配符
         */
        boolean b = ArrayAlg.hasNulls(new Pair<>());
        System.out.println(b);

    }

    static class ArrayAlg {

        @SuppressWarnings("unchecked")
        static <T extends Comparable> Pair<T> minmax(T[] ts){
            if (null == ts || ts.length == 0) {
                return null;
            }
            T min = ts[0];
            T max = ts[0];
            for (T t : ts) {
                if (max.compareTo(t) > 0) {
                    max = t;
                }
                if (min.compareTo(t) < 0) {
                    min = t;
                }
            }
            return new Pair<>(min, max);
        }

        /**
         * TODO 泛型方法
         *  泛型变量放在修饰符( 这里是 static ) 的后面 返回类型的前面
         *  泛型方法可以定义在普通类中，也可以定义在泛型类中
         */
        @SafeVarargs
        static <T> T getMiddle(T... a) {
            return a[a.length / 2];
        }

        /**
         * TODO 泛型变量限定
         *  使用 <T extends Comparable> 限定 'T' 必须是Comparable的子类
         *  在这里为什么使用 'extends' 而不是 'implements' 毕竟 'Comparable' 是一个接口
         *     'T' 应该是限定类型的子类型，'T' 的限定类型可以是类，也可以是接口。
         *     选择 'extends' 关键字的原因是更接近子类
         *  一个类型变量或者通配符可以有多个限定,例如用 '&' 分割, 而用逗号来分割类型变量
         *  参考 {@link java.util.Comparator }
         */
        @SuppressWarnings("unchecked")
        static <T extends Comparable & Serializable> T min (T[] ts) {
            if (null == ts || ts.length == 0) {
                return null;
            }
            T smallest = ts[0];
            for (T t : ts) {
                if (smallest.compareTo(t) > 0) {
                    smallest = t;
                }
            }
            return smallest;
        }

        static boolean hasNulls(Pair<?> p) {
            return p.getFirst() == null || p.getSecond() == null;
        }

        static void swap (Pair<?> p) {
            swapHelper(p);
        }

        static <T> void swapHelper(Pair<T> p){
            T first = p.getFirst();
            p.setFirst(p.getSecond());
            p.setSecond(first);
        }
    }

    @ToString
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    static abstract class Employee implements Comparable<Employee> {

        private Double bonus;

        @Override
        public int compareTo(Employee o) {
            return null == o ? 0 : Double.compare(bonus, o.getBonus());
        }
    }


    @NoArgsConstructor
    static class Manager extends Employee {

        public Manager(Double bonus) {
            super(bonus);
        }

        // public int compareTo(Employee o) {

    }

    static class Executive extends Manager {

    }

    private static void printBuddies(Pair<? extends Employee> pair){
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

    @SuppressWarnings("unchecked")
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

    @SuppressWarnings("unchecked")
    static <T extends Throwable> void throwAs (Throwable e) throws T {
        throw (T)e;
    }

}
