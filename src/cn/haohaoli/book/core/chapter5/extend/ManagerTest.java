package cn.haohaoli.book.core.chapter5.extend;

public class ManagerTest {

    /**
     * 方法调用
     * @param args
     */
    public static void main(String[] args) {

        Manager manager = new Manager("张三", 80000, 1987, 12, 15);
        manager.setBonus(5000d);
        Employee[] staff = new Employee[3];
        staff[0] = manager;
        staff[1] = new Employee("李四", 50000, 1989, 10, 1);
        staff[2] = new Employee("王五", 40000, 1990, 3, 15);

        /**
         * error
         * ClassCastException
         */
//        Manager boos = (Manager) staff[1];
        if(staff[1] instanceof Manager) {
            Manager boos = (Manager) staff[1];
        }

        //编译错误，这是因为String不是Employee的子类
//        String c = (String) staff[1];

        for (Employee employee : staff) {
            System.out.println(employee.getName() + " - " + employee.getSalary());
            /**
             * 张三 - 85000.0
             * 李四 - 50000.0
             * 王五 - 40000.0
             */
        }

        Manager[] managers = new Manager[10];
        staff = managers;
        /**
         * 抛出异常 ArrayStoreException
         * staff = managers
         * staff 和 managers 引用同一个数组
         *  staff[0] = new Employee("李四", 50000, 1989, 10, 1);   等于把一个职员加到经理的数组中
         *      所以调用设置奖金会错误
         *      managers[0].setBonus(99999d);   //错误
         */
        /*staff[0] = new Employee("李四", 50000, 1989, 10, 1);
        managers[0].setBonus(99999d);   //错误*/
        System.out.println(1);

        s(manager);

    }

    public static String s (String s) {

        return s;
    }

    public static Employee s (Employee s) {

        return s;
    }

    public static int s (int s) {

        return 1;
    }

    enum type {
        A,
        B;
    }
}
