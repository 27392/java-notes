package cn.haohaoli.book.headfirst.facade.version1;

import lombok.Getter;

/**
 * 灯光
 *
 * @author LiWenHao
 * @date 2019-06-12 23:21
 */
@Getter
public class Lights {

    private int brightness;

    public void dim(int brightness) {
        this.brightness = brightness;
        System.out.println("将灯光调整到: " + brightness + "%");
    }
}
