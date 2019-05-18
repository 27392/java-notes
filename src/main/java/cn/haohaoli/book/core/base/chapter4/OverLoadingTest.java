package cn.haohaoli.book.core.base.chapter4;

/**
 * 重载
 * @author LiWenHao
 * @date 2019-01-23 19:37
 */
public class OverLoadingTest {

    /**
     * TODO 重载解析(overloading resolution)
     *  参考资料：https://blog.csdn.net/Harrytsz/article/details/61920216
     *  如果多个方法(比如， StringBuilder 构造器方法)有 相同的名字、不同的参数， 便产生了重载。
     *  编译器必须挑选出具体执行哪个方法， 它通过用 各个方法给出的参数类型与特定方法调用所使用的值类型进行匹配来挑选出相应的方法。
     *  如 果编译器找不到匹配的参数， 就会产生编译时错误， 因为根本不存在匹配， 或者没有一个比 其他的更好。
     *  (这个过程被称为重载解析(overloading resolution)。)
     */
    public static void tripleValue(double x) {
        x = x * 3;
    }

    public static void tripleValue(Double x) {
        x = x * 3;
    }

    public static void tripleValue(String x) {
        x = "b";
    }

    public static void tripleValue(String[] x) {
        x[1] = "x";
    }

    public static void tripleValue(String s, String s1) {
        s = s1;
    }

    public static void tripleValue(Employee x) {
        x.raiseSalary(200d);
    }
}
