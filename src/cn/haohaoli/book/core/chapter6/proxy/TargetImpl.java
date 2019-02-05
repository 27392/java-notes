package cn.haohaoli.book.core.chapter6.proxy;

/**
 * @author LiWenHao
 * @date 2019-02-05 20:10
 */
public class TargetImpl implements Target {

    @Override
    public String execute() {
        System.out.println("TargetImpl execute");
        return "execute";
    }
}
