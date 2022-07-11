package cn.haohaoli.book.headfirst.facade.version2;

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

    public void on () {
        System.out.println("打开灯光");
    }

    public void dim(int brightness) {
        this.brightness = brightness;
        System.out.println("将灯光调整到: " + brightness + "%");
    }
}
