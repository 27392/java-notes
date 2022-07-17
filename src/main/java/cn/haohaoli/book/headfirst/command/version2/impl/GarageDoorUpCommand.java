package cn.haohaoli.book.headfirst.command.version2.impl;

import cn.haohaoli.book.headfirst.command.version2.Command;
import cn.haohaoli.book.headfirst.command.version2.GarageDoor;
import lombok.RequiredArgsConstructor;

/**
 * @author lwh
 */
@RequiredArgsConstructor
public class GarageDoorUpCommand implements Command {

    private final GarageDoor garageDoor;

    @Override
    public void execute() {
    }
}
