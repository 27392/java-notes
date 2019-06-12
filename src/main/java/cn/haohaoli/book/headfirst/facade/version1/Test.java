package cn.haohaoli.book.headfirst.facade.version1;

/**
 * @author LiWenHao
 * @date 2019-06-12 23:20
 */
public class Test {

    /**
     * 1.打开爆米花机
     * 2.开始爆米花
     * 3.将灯光调暗
     * 4.放下屏幕
     * 5.打开投影仪
     * 6.将投影仪的输入切换到DVD
     * 7.将投影仪设置在宽屏模式
     * 8.打开功放
     * 9.将功放的输入设置为DVD
     * 10.将功放设置为环绕立体声
     * 11.将功放音量调到中5
     * 12.打开DVD
     * 13.开始播放DVD
     */
    public static void main(String[] args) {

        PopcomPopper popper = new PopcomPopper();
        //1.打开爆米花机
        popper.on();
        //2.开始爆米花
        popper.pop();
        //3.将灯光调暗
        Lights lights = new Lights();
        lights.dim(10);
        //4.放下屏幕
        Screen screen = new Screen();
        screen.down();
        //5.打开投影仪
        DvdPlayer dvd = new DvdPlayer();
        Projector projector = new Projector();
        //6.将投影仪的输入切换到DVD
        projector.setInput(dvd);
        //7.将投影仪设置在宽屏模式
        projector.wideScreenMode();
        //8.打开功放
        Amplifier amp = new Amplifier();
        amp.on();
        //9.将功放的输入设置为DVD
        amp.setDvd(dvd);
        //10.将功放设置为环绕立体声
        amp.setSurroundSound();
        //11.将功放音量调到中5
        amp.setVolume(5);
        //12.打开DVD
        dvd.on();
        //13.开始播放DVD
        dvd.play("move");
    }
}
