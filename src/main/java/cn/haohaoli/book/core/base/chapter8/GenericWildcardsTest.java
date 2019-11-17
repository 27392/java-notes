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

        // 子类限定通配符
        Pair<Manager>            managerBuddies  = new Pair<>();
        printBuddies(managerBuddies);

        Pair<? extends Employee> wildcardBuddies = managerBuddies;  //OK
        // wildcardBuddies.setFirst(new Employee());                //错误

        // 超类型限定通配符
        Manager[]      managers     = {new Manager(2.0), new Manager(1.0)};
        Pair<Employee> employeePair = new Pair<>();

        minmaxBonus(managers, employeePair);
        minmaxBonus(managers, new Pair<Object>());
        System.out.println(employeePair);

        /**
         * TODO 超类型限定的另外用法
         *  Manager 继承了Employee,而Employee 拓展了 Comparable<Employee>
         *  因此 Manager 实现的是 Comparable<Employee>在这种情况下超类型可以补救
         *      private static <T extends Comparable<? super T>> T minBonus(T[] a)
         */
        Manager[] managerArray = {new Manager(2.0), new Manager(1.0)};
        Manager   manager      = minBonus(managerArray);
        System.out.println(manager);
    }

    private static void printBuddies(Pair<? extends Employee> pair) {
        Optional.ofNullable(pair).ifPresent(System.out::println);
    }

    private static void minmaxBonus(Manager[] a, Pair<? super Employee> result) {
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
            // int compareTo(? super T)
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
