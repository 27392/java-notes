package cn.haohaoli.book.core.base.chapter8;

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
        System.out.println("min = " + mm.getFirst());
        System.out.println("max = " + mm.getSecond());

        /**
         * 在调用一个泛型方法是，在方法名前的尖括号中放入具体的类型
         *  ArrayAlg.<String>getMiddle("John", "Q.", "Public");
         *  这种情况下方法可以省略<String>,编译器有足够的信息能够推断出所调用的方法。
         *   他用的入参类型 (即 String[]) 与泛型类 T[] 进行匹配并推断出 'T' 一定是String
         */
        String middle = ArrayAlg.getMiddle("John", "Q.", "Public");
        System.out.println(middle);

    }

    static class ArrayAlg {

        static Pair<String> minmax(String[] strings){
            if (null == strings || strings.length == 0) {
                return null;
            }
            String min = strings[0];
            String max = strings[0];
            for (String str : strings) {
                if (max.compareTo(str) > 0) {
                    max = str;
                }
                if (min.compareTo(str) < 0) {
                    min = str;
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
    }
}
