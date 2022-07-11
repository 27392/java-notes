package cn.haohaoli.book.headfirst.facade.version2;

import lombok.AllArgsConstructor;

/**
 * 家庭影院外观
 *
 * @author lwh
 */
@AllArgsConstructor
public class HomeTheaterFacade {

    private final PopcornPopper popper;
    private final Lights        lights;
    private final Screen        screen;
    private final Projector     projector;
    private final Amplifier     amp;
    private final DvdPlayer     dvd;

    public void watchMovie(String movie) {
        System.out.println("准备开始观看电影...");

        popper.on();
        popper.pop();
        lights.dim(10);
        screen.down();
        projector.on();
        projector.setInput(dvd);
        projector.wideScreenMode();
        amp.on();
        amp.setDvd(dvd);
        amp.setStereoSound();
        amp.setVolume(5);
        dvd.on();
        dvd.play(movie);
    }

    public void endMovie() {
        System.out.println("关闭电影院...");

        popper.off();
        lights.on();
        screen.up();
        projector.off();
        amp.off();
        dvd.stop();
        dvd.eject();
        dvd.off();
    }
}
