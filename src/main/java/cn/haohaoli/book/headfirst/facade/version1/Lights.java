package cn.haohaoli.book.headfirst.facade.version1;

/**
 * 灯光
 * @author LiWenHao
 * @date 2019-06-12 23:21
 */
public class Lights {

    private int brightness;

    public void on () {

    }

    public void off () {

    }

    //调暗
    public void dim (int brightness) {
        this.brightness = brightness;
    }
}
