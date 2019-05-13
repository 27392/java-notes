package cn.haohaoli.book.headfirst.singleton.type;

/**
 * @author LiWenHao
 * @date 2019-05-05 17:34
 */
public class StaticInnerClassSingleton {

    private StaticInnerClassSingleton(){}

    private static class InnerClass {
        private static StaticInnerClassSingleton staticInnerClassSingleton = new StaticInnerClassSingleton();
    }

    public StaticInnerClassSingleton getInstance() {
        return InnerClass.staticInnerClassSingleton;
    }
}
