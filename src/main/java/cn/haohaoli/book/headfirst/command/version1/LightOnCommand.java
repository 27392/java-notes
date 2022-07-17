package cn.haohaoli.book.headfirst.command.version1;

import lombok.RequiredArgsConstructor;

/**
 * @author lwh
 */
@RequiredArgsConstructor
public class LightOnCommand implements Command{

    private final Light light;

    @Override
    public void execute() {
        light.on();
    }
}
