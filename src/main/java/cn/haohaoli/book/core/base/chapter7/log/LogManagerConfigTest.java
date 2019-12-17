package cn.haohaoli.book.core.base.chapter7.log;

import cn.haohaoli.book.core.base.chapter7.log.config.LoadLogConfig;

import java.util.logging.Logger;

/**
 * 日志管理器配置
 *
 * @author LiWenHao
 */
public class LogManagerConfigTest {

    static {
        // 加载配置
        LoadLogConfig.loadConfig();
    }

    private final static Logger LOGGER = Logger.getLogger(LogManagerConfigTest.class.getName());

    /**
     * 由该方法加载 {@link java.util.logging.LogManager#readConfiguration()}
     */
    public static void main(String[] args) {
        LOGGER.fine("hello");
        LOGGER.info("xxxxxxx");
        LOGGER.warning("xxxxxxxxxxxxxxxxxx");
    }
}
