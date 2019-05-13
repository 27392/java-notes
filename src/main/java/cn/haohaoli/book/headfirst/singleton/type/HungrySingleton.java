package cn.haohaoli.book.headfirst.singleton.type;

/**
 * @author LiWenHao
 * @date 2019-05-05 15:55
 */
public class HungrySingleton {

    private static HungrySingleton singleton = new HungrySingleton();

    private HungrySingleton () {}

    /**
     * 饿汉模式
     * @return
     */
    public static HungrySingleton getInstance() {
        return singleton;
    }
}
