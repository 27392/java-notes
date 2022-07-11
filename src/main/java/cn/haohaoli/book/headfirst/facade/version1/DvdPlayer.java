package cn.haohaoli.book.headfirst.facade.version1;

/**
 * Dvd播放器
 * @author LiWenHao
 * @date 2019-06-12 23:17
 */
public class DvdPlayer {

    public void on() {
        System.out.println("打开DVD");
    }

    public void play(String name) {
        System.out.println("DVD开始播放: " + name);
    }
}
