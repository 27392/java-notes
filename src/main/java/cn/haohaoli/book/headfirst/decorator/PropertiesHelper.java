package cn.haohaoli.book.headfirst.decorator;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * @author LiWenHao
 * @date 2019-04-08 16:18
 */
public class PropertiesHelper {

    private PropertiesHelper() {
    }

    private static final Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemResourceAsStream("config/menu.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("PropertiesHelper 加载失败！");
        }
    }

    public static String getProperty (String name) {
        Objects.requireNonNull(name);
        return properties.getProperty(name);
    }

    public static double getPropertyForDouble (String name) {
        Objects.requireNonNull(name);
        return Double.parseDouble(getProperty(name));
    }

}
