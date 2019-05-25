package cn.haohaoli.book.core.base.chapter8;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 泛型测试类
 * @author LiWenHao
 * @date 2019/5/25 12:54
 */
public class Test {

    public static void main(String[] args) {

        //TODO JDK7之后的版本在构造函数中可以省略泛型类,省略的类型可以从变量的类型推断得出
        Pair<String> jdk7 = new Pair<String>();
        Pair<String> jdk8 = new Pair<>();

        String[] words = {"Mary", "had", "a", "little", "lamb"};

        Pair<String> mm = ArrayAlg.minmax(words);
        Optional.ofNullable(mm).ifPresent(pair -> System.out.println(pair.getFirst()));
        Optional.ofNullable(mm).ifPresent(pair -> System.out.println(pair.getSecond()));

        /**
         * 在调用一个泛型方法是，在方法名前的尖括号中放入具体的类型
         *  ArrayAlg.<String>getMiddle("John", "Q.", "Public");
         *  这种情况下方法可以省略<String>,编译器有足够的信息能够推断出所调用的方法。
         *   他用的入参类型 (即 String[]) 与泛型类 T[] 进行匹配并推断出 'T' 一定是String
         */
        String middle = ArrayAlg.getMiddle("John", "Q.", "Public");
        System.out.println(middle);

        String min = ArrayAlg.min(new String[]{"c", "z", "b"});
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
        Pair<Manager> managerPair = new Pair<>();
        // Pair<Employee> employeePairs = managerPair; // 错误
        List<Manager> managerList = new ArrayList<>();
        // List<Employee> employeeList = managerList;  //错误

    }

    static class ArrayAlg {

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
        static <T extends Comparable & Serializable> T min (T[] ts) {
            if (null == ts || ts.length == 0) {
                return null;
            }
            T smallest = ts[0];
            for (T t : ts) {
                if (smallest.compareTo(t) < 0) {
                    smallest = t;
                }
            }
            return smallest;
        }
    }


    static class Employee implements Comparable {

        @Override
        public int compareTo(Object o) {
            //空 不实现
            return 0;
        }
    }

    static class Manager extends Employee {

    }

}
