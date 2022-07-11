package cn.haohaoli.book.headfirst.facade.version2;

import lombok.Getter;

/**
 * 功放
 *
 * @author LiWenHao
 * @date 2019-06-12 23:16
 */
@Getter
public class Amplifier {

    private DvdPlayer dvd;

    private int volume;

    public void on() {
        System.out.println("打开功放");
    }

    public void off() {
        System.out.println("关闭功放");
    }

    public void setStereoSound() {
        System.out.println("功放设置立体声");
    }

    public void setDvd(DvdPlayer dvd) {
        this.dvd = dvd;
        System.out.println("功放输入设置为DVD");
    }

    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("将功放音量调到: " + volume);
    }
}
