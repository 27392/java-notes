package cn.haohaoli.book.core.base.chapter7.log;

import cn.haohaoli.book.core.base.chapter7.log.config.LoadLogConfig;

import java.io.IOException;
import java.util.logging.*;

/**
 *
 * @author LiWenHao
 */
public class LogHandlerTest {

    static {
        LoadLogConfig.loadConfig();
    }

    private final static Logger LOGGER = Logger.getLogger(LogHandlerTest.class.getName());

    public static void main(String[] args) throws IOException {
        LOGGER.setLevel(Level.FINE);
        LOGGER.setUseParentHandlers(false);             // 是否使用父处理器
        Handler consoleHandler = new ConsoleHandler();  // 控制台处理器
        consoleHandler.setLevel(Level.FINE);
        Handler fileHandler = new FileHandler();        // 文件处理器
        LOGGER.addHandler(consoleHandler);
        LOGGER.addHandler(fileHandler);
        LOGGER.setFilter((record)-> record.getMessage().contains("e")); // 过滤器
        LOGGER.fine("fine");
        LOGGER.info("info");
    }
}
