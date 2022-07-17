package cn.haohaoli.book.headfirst.command.version2;

import cn.haohaoli.book.headfirst.command.version2.impl.*;

/**
 * @author lwh
 */
public class Test {

    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();

        Light livingRoomLight  = new Light("客厅");
        Light kitchenRoomLight = new Light("厨房");

        CeilingFan livingRoomCeilingFan = new CeilingFan("客厅");
        GarageDoor garageDoor           = new GarageDoor();
        Stereo     stereo               = new Stereo("客厅");

        // 电灯命令
        LightOnCommand  livingRoomLightOn   = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOff  = new LightOffCommand(livingRoomLight);
        LightOnCommand  kitchenRoomLightOn  = new LightOnCommand(kitchenRoomLight);
        LightOffCommand kitchenRoomLightOff = new LightOffCommand(kitchenRoomLight);

        // 吊扇命令
        CeilingFanOffCommand livingRoomCeilingFanOff = new CeilingFanOffCommand(livingRoomCeilingFan);
        CeilingFanOnCommand  livingRoomCeilingFanOn  = new CeilingFanOnCommand(livingRoomCeilingFan);

        // 车库门命令
        GarageDoorUpCommand   garageDoorUp   = new GarageDoorUpCommand(garageDoor);
        GarageDoorDownCommand garageDoorDown = new GarageDoorDownCommand(garageDoor);

        // 设置命令
        remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remoteControl.setCommand(1, kitchenRoomLightOn, kitchenRoomLightOff);
        remoteControl.setCommand(2, livingRoomCeilingFanOn, livingRoomCeilingFanOff);
        remoteControl.setCommand(3, garageDoorUp, garageDoorDown);

        System.out.println(remoteControl);

        remoteControl.onButtonWasPushed(0);
        remoteControl.offButtonWasPushed(0);
        remoteControl.onButtonWasPushed(1);
        remoteControl.offButtonWasPushed(1);
        remoteControl.onButtonWasPushed(2);
        remoteControl.offButtonWasPushed(2);
        remoteControl.onButtonWasPushed(3);
        remoteControl.offButtonWasPushed(3);
    }

}
