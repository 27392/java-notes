package cn.haohaoli.book.headfirst.singleton.type;

/**
 * @author LiWenHao
 * @date 2019-05-05 16:32
 */
public class LazyDoubleCheckSingleton {

    //禁止重排序
    private volatile static LazyDoubleCheckSingleton singleton;

    private LazyDoubleCheckSingleton () {}


    public static LazyDoubleCheckSingleton getInstance() {
        if (singleton == null) {
            synchronized (LazyDoubleCheckSingleton.class){
                if (singleton == null) {
                    singleton = new LazyDoubleCheckSingleton();
                }
            }
        }
        return singleton;
    }
}
