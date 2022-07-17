package cn.haohaoli.book.headfirst.command.version2;

import lombok.AllArgsConstructor;

/**
 * 音响
 *
 * @author lwh
 */
@AllArgsConstructor
public class Stereo {

    private final String name;

    public void on() {
        System.out.println("打开音响");
    }

    public void off() {
        System.out.println("关闭音响");
    }

    void setCd() {

    }

    void setDvd() {

    }

    void setRadio() {

    }

    void setVolume(int volume) {

    }
}
