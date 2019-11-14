package cn.haohaoli.book.core.base.chapter8;

/**
 * 泛型继承
 *
 * @author LiWenHao
 */
public class GenericExtendsTest {

    public static void main(String[] args) {
        Pair<Manager>  managerPair   = new Pair<>();
        // Pair<Employee> employeePairs = managerPair; // 错误 Pair<Manager>并不是Pair<Employee>的子类
    }


    private static class Employee<T> {

        private T ext;
    }

    // 泛型类可以实现或继承其他泛型类
    private static class Manager extends Employee<String> implements Comparable<Manager> {

        @Override
        public int compareTo(Manager o) {
            return 0;
        }
    }

}
