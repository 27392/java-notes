package cn.haohaoli.book.headfirst.command.version2;

import lombok.AllArgsConstructor;

/**
 * 电灯
 *
 * @author lwh
 */
@AllArgsConstructor
public class Light {

    private final String name;

    public void on() {
        System.out.println("打开" + name + "电灯");
    }

    public void off() {
        System.out.println("关闭" + name + "电灯");
    }
}
