package cn.haohaoli.book.headfirst.singleton.type;

/**
 * @author LiWenHao
 * @date 2019-05-05 15:14
 */
public class LazySingleton {

    private static LazySingleton uniqueInstance;

    private LazySingleton() {}

    /**
     * 懒汉式 (线程不安全)
     * @return
     */
    public static LazySingleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new LazySingleton();
        }
        return uniqueInstance;
    }


    /**
     * 懒汉式 同步方法 (线程安全)
     * @return
     */
    public static synchronized LazySingleton getSynchronizedInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new LazySingleton();
        }
        return uniqueInstance;
    }
}
