package cn.haohaoli.book.core.chapter4;

public class MethodParamTest {

    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new Thread(()-> {
            System.out.println("关闭咯");
        }));

        Employee employee0 = new Employee();
        Employee employee9 = new Employee("1");


        /**
         * 方法不能修改一个基本类型参数的值
         */
        double salary = 100d;
        tripleValue(salary);
        System.out.println(salary);

        Double salary1 = 100d;
        tripleValue(salary1);
        System.out.println(salary1);

        /**
         * 方法参数是对象的时候可以方法可以修改对象内的值
         * 因为方法参数得到的是对象引用的拷贝,对象拷贝和其他的拷贝同时引用同一个对象
         */
        Employee employee = new Employee("1", 100d);
        tripleValue(employee);
        System.out.println(employee);

        /**
         *
         */
        Employee x = new Employee("1", 600d);
        Employee y = new Employee("2", 100d);
        swap(x, y);
        System.out.println(x);
        System.out.println(y);

    }

    public static void tripleValue(double x) {
        x = x * 3;
    }

    public static void tripleValue(Double x) {
        x = x * 3;
    }

    public static void tripleValue(Employee x) {
        x.setSalary(500d);
    }

    /**
     * 对象交换
     * @param x
     * @param y
     */
    public static void swap (Employee x, Employee y) {
        Employee temp = x;
        x = y;
        y = temp;
    }
}
