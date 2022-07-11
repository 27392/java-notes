package cn.haohaoli.book.headfirst.facade.version2;

/**
 * Dvd播放器
 * @author LiWenHao
 * @date 2019-06-12 23:17
 */
public class DvdPlayer {

    public void on() {
        System.out.println("打开DVD");
    }

    public void off() {
        System.out.println("关闭DVD");
    }

    public void stop() {
        System.out.println("暂停DVD");
    }

    public void eject() {
        System.out.println("弹出DVD");
    }

    public void play(String name) {
        System.out.println("DVD开始播放: " + name);
    }
}
