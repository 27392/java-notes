package cn.haohaoli.book.headfirst.facade.version1;

import lombok.Getter;
import lombok.Setter;

/**
 * 功放
 * @author LiWenHao
 * @date 2019-06-12 23:16
 */
@Getter
@Setter
public class Amplifier {

    private DvdPlayer dvd;

    private CdPlayer cd;

    private int volume;

    public void on() {

    }

    public void off() {

    }

    //设置立体声
    public void setStereoSound(){

    }

    //设置环绕声
    public void setSurroundSound() {

    }

}
