package cn.haohaoli.book.core.chapter5.equals;

/**
 * TODO equals 方法
 *  Object 类中的 equals 方法用于检测一个对象是否等于另外一个对象。
 *  在 Object 类中， 这个方法将判断两个对象是否具有相同的引用。如果两个对象具有相同的引用， 它们一定是相等的
 *  Equals 与 hashCode 的定义必须一致: 如果 x.equals(y) 返回 true, 那么 x.hashCode( ) 就必 须与 y.hashCode( ) 具有相同的值
 * @author LiWenHao
 * @date 2019-01-23 22:05
 */
public class EqualsTest {

    public static void main(String[] args) {
        /**
         * TODO == 和 equals 的区别
         *  资料：
         *      https://www.cnblogs.com/Eason-S/p/5524837.html
         *      https://www.cnblogs.com/tinyphp/p/3768214.html
         *
         */
        Employee alice1 = new Employee("a",75000,1987,12,15);
        Employee alice2 = alice1;
        Employee alice3 = new Employee("a",75000,1987,12,15);
        Employee bob = new Employee("b",50000,1989,10,1);
        //地址是否一致
        System.out.println("alice1 == alice2: " + (alice1 == alice2));
        System.out.println("alice1 == alice3: " + (alice1 == alice3));
        //内容是否一致
        System.out.println("alice1.equals(alice3): " + (alice1.equals(alice3)));
        System.out.println("alice1.equals(bob): " + (alice1.equals(bob)));
        System.out.println("bob.toString: " + bob);
        System.out.println("===============================================================");
        Manager carl = new Manager("c", 80000, 1987, 12, 15);
        Manager boss = new Manager("c", 80000, 1987, 12, 15);
        boss.setBonus(5000);
        System.out.println("boss.toString(): " + boss);
        System.out.println("carl.equals(boss): " + carl.equals(boss));
        System.out.println("alice1.hashCode(): " + alice1.hashCode());
        System.out.println("alice3.hashCode(): " + alice3.hashCode());
        System.out.println("bob.hashCode(): " + bob.hashCode());
        System.out.println("carl.hashCode(): " + carl.hashCode());

        //以 Class 对象的形式返回这个类的超类信息
        System.out.println(Manager.class.getSuperclass().getName());
    }
}
