package cn.haohaoli.book.boast.simplefactory;

/**
 * 算法类
 * @author LiWenHao
 * @date 2019/2/25 11:20
 */
public abstract class Algorithm {

    protected String a;
    protected String b;

    public abstract String getResult();

    public void setA(String a) {
        this.a = a;
    }

    public void setB(String b) {
        this.b = b;
    }
}
