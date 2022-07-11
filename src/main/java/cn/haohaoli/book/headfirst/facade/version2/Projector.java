package cn.haohaoli.book.headfirst.facade.version2;

/**
 * 投影仪
 * @author LiWenHao
 * @date 2019-06-12 23:17
 */
public class Projector {

    private DvdPlayer input;

    public void on () {
        System.out.println("打开投影仪");
    }

    public void off () {
        System.out.println("关闭投影仪");
    }

    public void wideScreenMode () {
        System.out.println("投影仪设置为宽屏模式");
    }

    public void setInput(DvdPlayer input) {
        this.input = input;
        System.out.println("投影仪输入切换到DVD");
    }
}
