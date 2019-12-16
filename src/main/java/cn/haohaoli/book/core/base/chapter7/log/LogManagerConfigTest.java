package cn.haohaoli.book.core.base.chapter7.log;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * 日志管理器配置
 * @author LiWenHao
 */
public class LogManagerConfigTest {

    private final static Logger LOGGER;

    // 加载配置
    static {
        if (Objects.isNull(System.getProperty("java.util.logging.config.file"))) {
            URL logConfig = LogManagerConfigTest.class.getClassLoader().getResource("logging.properties");
            Objects.requireNonNull(logConfig, "日志配置不存在");
            URI uri;
            try {
                uri = logConfig.toURI();
            } catch (URISyntaxException e) {
                throw new RuntimeException("加载日志配置错误");
            }
            System.setProperty("java.util.logging.config.file", Paths.get(uri).toString());
        }
    }

    // 初始化日志记录器
    static {
        LOGGER = Logger.getLogger("cn.haohaoli.book.core.base.chapter7.log.LogManagerConfigTest");
    }

    /**
     * 由该方法加载 {@link java.util.logging.LogManager#readConfiguration()}
     */
    public static void main(String[] args){
        LOGGER.fine("fine");
    }
}
