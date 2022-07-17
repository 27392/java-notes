package cn.haohaoli.book.headfirst.command.version2.impl;

import cn.haohaoli.book.headfirst.command.version2.Command;
import cn.haohaoli.book.headfirst.command.version2.Light;
import lombok.RequiredArgsConstructor;

/**
 * @author lwh
 */
@RequiredArgsConstructor
public class LightOnCommand implements Command {

    private final Light light;

    @Override
    public void execute() {
        light.on();
    }
}
