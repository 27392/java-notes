package cn.haohaoli.book.core.base.chapter7.log.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * 自定义日志格式化类
 * @author LiWenHao
 */
public class SimpleFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        return new Result(record).toString();
    }

    static class Result {

        private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        private String  date;
        private String  level;
        private String  className;
        private String  methodName;
        private String  message;
        private Integer theadId;

        public Result(LogRecord record) {
            this.date       = dateTimeFormatter.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(record.getMillis()), ZoneId.systemDefault()));
            this.level      = record.getLevel().getName();
            this.className  = record.getSourceClassName();
            this.methodName = record.getSourceMethodName();
            this.message    = record.getMessage();
            this.theadId    = record.getThreadID();
        }

        @Override
        public String toString() {
            return String.format("%s [%s] %s %s [%s] - %s\n", date, theadId, level, className, methodName, message);
        }
    }
}
