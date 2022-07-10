package cn.haohaoli.book.headfirst.singleton.type;

/**
 * 饿汉模式
 * @author LiWenHao
 * @date 2019-05-05 15:55
 */
public class HungrySingleton {

    private static final HungrySingleton singleton = new HungrySingleton();

    private HungrySingleton () {}

    public static HungrySingleton getInstance() {
        return singleton;
    }
}
