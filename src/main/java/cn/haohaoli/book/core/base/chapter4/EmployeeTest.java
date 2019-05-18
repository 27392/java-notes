package cn.haohaoli.book.core.base.chapter4;

/**
 * @author liWenHao
 * @date 2019/1/23 05:05
 */
public class EmployeeTest {

    public static void main(String[] args) {
        //在这个程序中， 构造了一个 Employee 数组， 并填人了三个雇员对象:
        Employee[] staffStatic = new Employee[3];
        staffStatic[0] = new Employee("张三",10000d, 1987, 10, 24);
        staffStatic[1] = new Employee("王五",10300d, 1983, 6, 30);
        staffStatic[2] = new Employee("赵四",4050d, 1981, 4, 4);
        for (Employee employee : staffStatic) {
            //利用 Employee 类的 raiseSalary 方法将每个雇员的薪水提高 5%
            employee.raiseSalary(5d);
            //每个Employee对象共享一个id static 修饰
            employee.setId();
        }

        for (Employee employee : staffStatic) {
            System.out.println("id=" + employee.getId() + ",name=" + employee.getName() +
                    ",salary=" + employee.getSalary() +
                    ",hireDay=" + employee.getHireDay());
        }

        int id = Employee.getNextId();
        System.out.println(id);
    }
}
