package cn.haohaoli.book.core.base.chapter7.log.config;

import cn.haohaoli.book.core.base.chapter7.log.LogManagerConfigTest;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * 日志配置加载类
 * @author LiWenHao
 */
public class LoadLogConfig {

    /**
     * 加载配置
      */
    public static void loadConfig() {
        if (Objects.isNull(System.getProperty("java.util.logging.config.file"))) {
            URL logConfig = LogManagerConfigTest.class.getClassLoader().getResource("log/logging.properties");
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
}
