package cn.haohaoli.book.core.chapter7.exception;

import java.io.IOException;

/**
 * TODO 创建异常类
 *  在程序中， 可能会遇到任何标准异常类都没有能够充分地描述清楚的问题。
 *  在这种情况下， 创建自己的异常类就是一件顺理成章的事情了。
 *  我们需要做的只是定义一个派生于 Exception 的类， 或者派生于 Exception 子类的类。
 *  例如，定义一个派生于IOException的类。 习惯上， 定义的类应该包含两个构造器， 一个是默认的构造器; 另一个是带有详细描述信息的构造器
 *  (超类Throwable的toString方法将会打印出这些详细信息， 这在调试中非常有用)。
 * @author LiWenHao
 * @date 2019-02-05 22:30
 */
public class FileFormatException extends IOException {

    public FileFormatException() {
    }

    public FileFormatException(String message) {
        super(message);
    }
}
