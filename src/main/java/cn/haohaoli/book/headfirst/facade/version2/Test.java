package cn.haohaoli.book.headfirst.facade.version2;

/**
 * @author lwh
 */
public class Test {

    public static void main(String[] args) {
        PopcornPopper popper    = new PopcornPopper();
        Lights        lights    = new Lights();
        Screen        screen    = new Screen();
        Projector     projector = new Projector();
        Amplifier     amp       = new Amplifier();
        DvdPlayer     dvd       = new DvdPlayer();

        HomeTheaterFacade homeTheater = new HomeTheaterFacade(popper, lights, screen, projector, amp, dvd);
        homeTheater.watchMovie("movie");

        System.out.println();

        homeTheater.endMovie();
    }
}
