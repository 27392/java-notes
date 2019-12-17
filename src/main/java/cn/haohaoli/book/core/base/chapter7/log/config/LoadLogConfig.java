package cn.haohaoli.book.core.base.chapter7.log.config;

import cn.haohaoli.book.core.base.chapter7.log.LogManagerConfigTest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.*;

/**
 * 日志配置加载类
 * @author LiWenHao
 */
public class LoadLogConfig {

    private static Boolean notLoaded = Objects.isNull(System.getProperty("java.util.logging.config.file"))
            || Objects.isNull(System.getProperty("java.util.logging.config.class"));

    /**
     * 加载外部配置
     */
    public static void loadExternalConfig() {
        if (notLoaded) {
            URL logConfig = LogManagerConfigTest.class.getClassLoader().getResource("log/logging.properties");
            Objects.requireNonNull(logConfig, "日志配置不存在");
            URI uri;
            try {
                uri = logConfig.toURI();
            } catch (URISyntaxException e) {
                throw new RuntimeException("加载日志配置错误");
            }
            System.setProperty("java.util.logging.config.file", Paths.get(uri).toString());
            notLoaded = false;
        }
    }

    /**
     * 加载配置
     */
    public static void loadConfig()  {
        if (notLoaded) {
            // 最终的处理器
            Logger logger = Logger.getLogger("");
            logger.setLevel(Level.FINE);

            // 格式化器
            SimpleFormatter formatter = new SimpleFormatter();

            // 控制台处理器
            Handler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.FINE);
            consoleHandler.setFormatter(formatter);
            Handler[] handlers = logger.getHandlers();
            for (Handler handler : handlers) {
                if (handler instanceof ConsoleHandler) {
                    logger.removeHandler(handler);
                }
            }
            logger.addHandler(consoleHandler);
            try {
                // 文件处理器
                Handler fileHandler = new FileHandler("%h/app-%u.log", 5000, 1, true);
                fileHandler.setLevel(Level.FINE);
                fileHandler.setFormatter(formatter);
                logger.addHandler(fileHandler);
            } catch (IOException e) {
                logger.log(Level.WARNING, "日志配置加载失败", e);
            }
            notLoaded = false;
        }
    }
}
