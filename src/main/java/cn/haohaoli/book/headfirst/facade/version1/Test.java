package cn.haohaoli.book.headfirst.facade.version1;

/**
 * @author LiWenHao
 * @date 2019-06-12 23:20
 */
public class Test {

    public static void main(String[] args) {

        PopcornPopper popper = new PopcornPopper();
        Lights        lights = new Lights();
        Screen       screen    = new Screen();
        Projector    projector = new Projector();
        Amplifier    amp       = new Amplifier();
        DvdPlayer    dvd       = new DvdPlayer();

        // 1.打开爆米花机
        popper.on();

        // 2.开始爆米花
        popper.pop();

        // 3.将灯光调暗
        lights.dim(10);

        // 4.放下屏幕
        screen.down();

        // 5.打开投影仪
        projector.on();

        // 6.将投影仪的输入切换到DVD
        projector.setInput(dvd);

        // 7.将投影仪设置在宽屏模式
        projector.wideScreenMode();

        // 8.打开功放
        amp.on();

        // 9.将功放的输入设置为DVD
        amp.setDvd(dvd);

        // 10.将功放设置为环绕立体声
        amp.setStereoSound();

        // 11.将功放音量调到5
        amp.setVolume(5);

        // 12.打开DVD
        dvd.on();

        // 13.开始播放DVD
        dvd.play("movie");
    }
}
