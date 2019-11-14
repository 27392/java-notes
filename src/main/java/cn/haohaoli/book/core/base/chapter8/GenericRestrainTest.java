package cn.haohaoli.book.core.base.chapter8;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.IntFunction;
import java.util.function.Supplier;

/**
 * 泛型约束
 *
 * @author LiWenHao
 * @date 2019/10/17 9:54
 */
public class GenericRestrainTest {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        /**
         * 不能使用基本类型实例化类型参数,要使用对应的包装类.
         *  InsideClass<int> insideClass = new InsideClass<int>();         //错误
         *  InsideClass<Integer> integerInsideClass = new InsideClass<>(); //ok
         */
        SimpleGeneric<Integer> integerSimpleGeneric = new SimpleGeneric<>();

        print(new SimpleGeneric<>(), new SimpleGeneric<>());

        // 由于不能直接实例化类型变量,所以可以通过反射或者JDK8中的Supplier函数接口来实例化
        SimpleGeneric<String> classPair    = SimpleGeneric.make(String.class);
        SimpleGeneric<String> supplierPair = SimpleGeneric.make(String::new);

        // 就像不能实例化一个泛型实例一样,也不能实例化数组. 也可以用反射或者Supplier来实例化
        System.out.println(Arrays.toString(minmax(String.class, "a", "b", "c")));
        System.out.println(Arrays.toString(minmax(String[]::new, "c", "b", "a")));
        System.out.println(Arrays.toString(minmax(() -> new String[2], "b", "d", "x")));

        // 可以使用异常泛型变量
        isEmpty("xx", new RuntimeException());
        try {
            isEmpty("io", new IOException());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class SimpleGeneric<T> {

        private T   field;
        private T[] fields;

        SimpleGeneric(T field) {
            this.field = field;
        }

        // 不能在静态域或方法中引用类型变量
        // private static T staticField;  //错误

        SimpleGeneric() {
            // 不能实例化类型变量,类型擦除会将T改变成Object.而我们不希望调用new Object()
            // field = new T();      //错误
            // 也不能实例化泛型数组
            // fields = new T[10](); //错误
        }

        /**
         * 使用反射创建
         *
         * @param clazz
         * @param <T>
         * @return
         * @throws IllegalAccessException
         * @throws InstantiationException
         */
        static <T> SimpleGeneric<T> make(Class<T> clazz) throws IllegalAccessException, InstantiationException {
            return new SimpleGeneric<>(clazz.newInstance());
        }

        /**
         * 使用Supplier(函数式接口)创建
         *
         * @param supplier
         * @param <T>
         * @return
         */
        static <T> SimpleGeneric<T> make(Supplier<T> supplier) {
            return new SimpleGeneric<>(supplier.get());
        }
    }

    // 泛型类不能拓展异常Throwable(包含所有的子类)都是不合法的
    // static class Ex<T> extends Throwable{}

    // 不能捕获泛型异常类
    public static <X extends Throwable> void tryEx(String str, Class<X> x) {
        /*
        try {

        } catch (X x){  //不能catch类型变量,错误

        }
        */
    }

    // 在异常规范中使用类型变量是允许的
    private static <X extends Throwable> void isEmpty(String str, X x) throws X {
        if (str == null || "".equals(str)) {
            throw x;
        }
    }

    /**
     * 为了调用这个方法虚拟机就必须创建一个T[]的数组,这就违反了"不能创建泛型数组"这个规则
     * 不过,对于这种情况,规则有所放松,你只会得到一个警告,而不是错误
     * 可以 '@SafeVarargs' 或者 '@SuppressWarnings("unchecked")' 来消除警告
     */
    @SafeVarargs
    private static <T> void print(T... ts) {
        for (T t : ts) {
            System.out.println(t.getClass().getSimpleName());
        }
    }

    /**
     * 使用IntFunction函数接口创建
     *
     * @param function
     * @param a
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    private static <T extends Comparable> T[] minmax(IntFunction<T[]> function, T... a) {
        if (null == a || a.length == 0) {
            return null;
        }
        T min = a[0];
        T max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (max.compareTo(a[i]) > 0) {
                max = a[i];
            }
            if (min.compareTo(a[i]) < 0) {
                min = a[i];
            }
        }
        T[] ts = function.apply(2);
        ts[0] = max;
        ts[1] = min;
        return ts;
    }

    /**
     * 使用Supplier函数接口创建
     *
     * @param function
     * @param a
     * @param <T>
     * @return
     */
    private static <T extends Comparable> T[] minmax(Supplier<T[]> function, T... a) {
        if (null == a || a.length == 0) {
            return null;
        }
        T min = a[0];
        T max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (max.compareTo(a[i]) > 0) {
                max = a[i];
            }
            if (min.compareTo(a[i]) < 0) {
                min = a[i];
            }
        }
        T[] ts = function.get();
        ts[0] = max;
        ts[1] = min;
        return ts;
    }

    /**
     * 使用反射创建
     *
     * @param clazz
     * @param a
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    private static <T extends Comparable> T[] minmax(Class<T> clazz, T... a) {
        if (null == a || a.length == 0) {
            return null;
        }
        T min = a[0];
        T max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (max.compareTo(a[i]) > 0) {
                max = a[i];
            }
            if (min.compareTo(a[i]) < 0) {
                min = a[i];
            }
        }
        T[] ts = (T[]) Array.newInstance(clazz, 2);
        ts[0] = max;
        ts[1] = min;
        return ts;
    }

}
