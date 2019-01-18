package cn.haohaoli.book.core.chapter6.interfaces;

/**
 * @author liWenHao
 * @date 2019/1/5 15:56
 **/
public class CloneTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        Employee c = new Employee("c", 75000);
        c.setHireDay(2019, 1, 1);
        Employee copy = c.clone();
        c.setName("x");
        c.raiseSalary(100d);
        c.setHireDay(2018, 1, 1);
        System.out.println("c= " + c);
        System.out.println("copy= " + copy);
        int[] x = {1, 2, 3, 4, 5};
        int[] clone1 = x.clone();
    }
}
