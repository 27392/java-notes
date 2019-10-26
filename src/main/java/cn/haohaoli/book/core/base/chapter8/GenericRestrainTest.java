package cn.haohaoli.book.core.base.chapter8;

/**
 * 泛型约束
 *
 * @author LiWenHao
 * @date 2019/10/17 9:54
 */
public class GenericRestrainTest {

    //@SuppressWarnings("unchecked")
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        print(new Pair<>(), new Pair<>());

        Pair<String> classPair    = Pair.makePair(String.class);
        Pair<String> supplierPair = Pair.makePair(String::new);
    }

    /**
     * 打印(泛型可变参方法)
     *
     * @param ts
     * @param <T>
     */
    @SafeVarargs
    private static <T> void print(T... ts) {
        for (T t : ts) {
            System.out.println(t.getClass().getSimpleName());
        }
    }
}
