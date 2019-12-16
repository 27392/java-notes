package cn.haohaoli.book.core.base.chapter7.log;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TODO 日志
 * @author LiWenHao
 */
public class LogTest {

    private final static Logger LOGGER = Logger.getLogger("cn.haohaoli.book.core.base.chapter7.log");

    public static void main(String[] args) {
//        simpleLogRecordExample();                         // 简单的记录日志
//        childLoggerLevelExample();                        // 测试父子记录器继承
        // TODO 日志记录器
        // 以下方法内方法级别都在 `FINER` 所以默认是不显示的.
        String r = enteringAndExitingLogExample("this is a log");   // 入参出参日志记录
        exceptionLogExample("nonexistent class");             // 异常日志记录
    }

    /**
     * 简单的记录日志
     */
    private static void simpleLogRecordExample() {
        Logger logger = Logger.getGlobal();
        logger.info("基本的日志记录");
    }

    /**
     * 日志父子关系测试
     *  当前日志记录器为 `cn.haohaoli.book.core.base.chapter7.log` {@link LOGGER}
     *  其余有三个日志记录器分别为
     *   `cn.haohaoli.book.core.base.chapter7.log.a`     (子包)    {@link classA#LOGGER}
     *   `cn.haohaoli.book.core.base.chapter7.log.b`     (子包)    {@link ClassB#LOGGER}
     *   `cn.haohaoli.book.core.base.chapter7.exception` (同级包)  {@link ClassC#LOGGER}
     */
    private static void childLoggerLevelExample() {
        // 将当前级别设置为 `WARNING`,子包(子记录器)同理只能输出 `WARNING`或以上级别日记
        // 而其他包(非子记录器)不收任何影响
        LOGGER.setLevel(Level.WARNING);
        LOGGER.severe("sever");
        LOGGER.log(Level.WARNING, "warning");
        classA.log();
        ClassB.log();
        ClassC.log();
    }

    /**
     * 入参和出参日志(级别:FINER)
     */
    public static String enteringAndExitingLogExample (String str) {
        LOGGER.entering(LogTest.class.getName(), "enteringAndExitingLogExample", str);
        String concat = str.concat(" - ").concat(str);
        LOGGER.exiting(LogTest.class.getName(), "enteringAndExitingLogExample", concat);
        return concat;
    }

    /**
     * 异常日志(级别:FINER)
     */
    public static void exceptionLogExample (String className) {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            // 记录异常有以下两种方式
            LOGGER.throwing(LogTest.class.getName(), "exceptionLogExample", e);
            // LOGGER.log(Level.WARNING, "错误", e);
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // 模拟子包内容
    ///////////////////////////////////////////////////////////////////////////

    private static class classA {

        private final static Logger LOGGER = Logger.getLogger("cn.haohaoli.book.core.base.chapter7.log.a");

        public static void log() {
            LOGGER.info("classA");
            LOGGER.severe("classA");
        }
    }

    private static class ClassB {

        private final static Logger LOGGER = Logger.getLogger("cn.haohaoli.book.core.base.chapter7.log.a.b");

        public static void log() {
            LOGGER.info("ClassB");
            LOGGER.severe("ClassB");
        }
    }

    private static class ClassC {

        private final static Logger LOGGER = Logger.getLogger("cn.haohaoli.book.core.base.chapter7.exception");

        public static void log() {
            LOGGER.info("ClassC");
            LOGGER.severe("ClassC");
        }
    }

}
