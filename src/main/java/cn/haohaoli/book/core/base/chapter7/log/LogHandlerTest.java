package cn.haohaoli.book.core.base.chapter7.log;

import cn.haohaoli.book.core.base.chapter7.log.config.LoadLogConfig;

import java.io.IOException;
import java.util.logging.*;

/**
 * TODO 日志处理器
 * @author LiWenHao
 */
public class LogHandlerTest {

    static {
        LoadLogConfig.loadExternalConfig();
    }

    private final static Logger LOGGER = Logger.getLogger(LogHandlerTest.class.getName());

    public static void main(String[] args) throws IOException {
        // 是否使用父处理器
        LOGGER.setUseParentHandlers(false);
        // 控制台处理器
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.FINE);
        // 文件处理器
        Handler fileHandler = new FileHandler();
        LOGGER.addHandler(consoleHandler);
        LOGGER.addHandler(fileHandler);
        // 过滤器
        LOGGER.setFilter((record)-> record.getMessage().contains("e"));

        LOGGER.fine("fine");
        LOGGER.info("info");
    }
}
