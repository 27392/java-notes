package cn.haohaoli.book.headfirst.command.version2.impl;

import cn.haohaoli.book.headfirst.command.version2.CeilingFan;
import cn.haohaoli.book.headfirst.command.version2.Command;
import lombok.RequiredArgsConstructor;

/**
 * @author lwh
 */
@RequiredArgsConstructor
public class CeilingFanOnCommand implements Command {

    private final CeilingFan ceilingFan;

    @Override
    public void execute() {
    }
}
