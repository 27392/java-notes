package cn.haohaoli.book.headfirst.command.version1;

/**
 * @author lwh
 */
public class Test {

    public static void main(String[] args) {
        SimpleRemoteControl remote = new SimpleRemoteControl();
        remote.setCommand(new LightOnCommand(new Light()));
        remote.buttonWasPressed();
    }
}
