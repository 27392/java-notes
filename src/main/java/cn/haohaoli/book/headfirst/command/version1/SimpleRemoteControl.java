package cn.haohaoli.book.headfirst.command.version1;

/**
 * 遥控器
 *
 * @author lwh
 */
public class SimpleRemoteControl {

    /**
     * 插槽
     */
    private Command slot;

    /**
     * 设置插槽命令
     *
     * @param command 命令
     */
    public void setCommand(Command command) {
        this.slot = command;
    }

    /**
     * 按钮被按下
     */
    public void buttonWasPressed() {
        slot.execute();
    }
}
