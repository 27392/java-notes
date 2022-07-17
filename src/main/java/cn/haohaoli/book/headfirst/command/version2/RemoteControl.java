package cn.haohaoli.book.headfirst.command.version2;

import cn.haohaoli.book.headfirst.command.version2.impl.NoCommand;

/**
 * 遥控器
 *
 * @author lwh
 */
public class RemoteControl {

    private final Command[] onCommands;
    private final Command[] offCommands;

    public RemoteControl() {
        this.onCommands = new Command[7];
        this.offCommands = new Command[7];

        NoCommand noCommand = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
    }

    public void setCommand(int slot, Command on, Command off) {
        onCommands[slot] = on;
        offCommands[slot] = off;
    }

    public void onButtonWasPushed(int slot) {
        onCommands[slot].execute();
    }

    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < onCommands.length; i++) {
            sb.append("[slot ").append(i).append("] ");
            sb.append(onCommands[i].getClass().getSimpleName());
            sb.append("\t\t");
            sb.append(offCommands[i].getClass().getSimpleName());
            sb.append("\n");
        }
        return sb.toString();
    }
}
