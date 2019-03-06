package cn.haohaoli.book.core.chapter7.exception;

import java.io.*;
import java.net.URL;
import java.util.Objects;

/**
 * TODO 异常
 *  在 Java 程序设计语言中， 异常对象都是派生于 Throwable 类的一个实例
 *  需要注意的是，所有的异常都是由 Throwable 继承而来， 但在下一层立即分解为两个分支:Error和Exception:
 *   Error:
 *      类层次结构描述了 Java 运行时系统的内部错误和资源耗尽错误。
 *      应用程序不应该 抛出这种类型的对象。 如果出现了这样的内部错误， 除了通告给用户， 并尽力使程序安全地 终止之外， 再也无能为力了。这种情况很少出现。
 *   Exception:
 *      在设计 Java 程序时， 需要关注 Exception 层次结构。
 *      这个层次结构又分解为两个分支: 一个分支派生于RuntimeException;
 *      另一个分支包含其他异常。划分两个分支的规则是:由 程序错误导致的异常属于RuntimeException; 而程序本身没有问题，
 *      但由于像I/O错误这类 问题导致的异常属于其他异常:
 *        派生于 RuntimeException 的异常包含下面几种情况:
 *           •错误的类型转换。
 *           • 数组访问越界
 *           • 访问 null 指针
 *        不是派生于 RuntimeException 的异常包括:
 *           • 试图在文件尾部后面读取数据。
 *           • 试图打开一个不存在的文件
 *           • 试图根据给定的字符串查找 Class 对象， 而这个字符串表示的类并不存在
 *   Java 语言规范将派生于 Error 类或 RuntimeException类的所有异常称为非受查(unchecked)异常，所有其他的异常称为受查(checked)异常
 * @author LiWenHao
 * @date 2019-02-05 21:45
 */
public class ExceptionTest {

    /**
     * TODO 声明受查异常
     *  在自己编写方法时， 不必将所有可能抛出的异常都进行声明。
     *  至于什么时候需要在方法中用 throws 子句声明异常， 什么异常必须使用throws子句声明， 需要记住在遇到下面4种情况时应该抛出异常:
     *   1 ) 调用一个抛出受査异常的方法， 例如，FileInputStream 构造器。
     *   2 ) 程序运行过程中发现错误， 并且利用 throw 语句抛出一个受查异常
     *   3 ) 程序出现错误， 例如， a[-1]= 0 会抛出一个 ArrayIndexOutOfBoundsException 这样的非受查异常。
     *   4 ) Java 虚拟机和运行时库出现的内部错误。
     *  如果出现前两种情况之一， 则必须告诉调用这个方法的程序员有可能抛出异常。 为什么? 因为任何一个抛出异常的方法都有可能是一个死亡陷阱。
     *  如果没有处理器捕获这个异常， 当前执行的线程就会结束。
     *  对于那些可能被他人使用的Java方法，应该根据异常规范(exception specification), 在 方法的首部声明这个方法可能抛出的异常。
     *  如果一个方法有可能抛出多个受查异常类型， 那么就必须在方法的首部列出所有的异常类。 每个异常类之间用逗号隔开
     *    不需要声明 Java 的内部错误， 即从Error继承的错误。任何程序代码都具有抛出那些异常的潜能， 而我们对其没有任何控制能力。
     *  同样， 也不应该声明从 RuntimeException 继承的那些非受查异常
     *    一个方法必须声明所有可能抛出的受查异常， 而非受查异常要么不可控制(Error), 要么就应该避免发生(RuntimeException)。
     *  如果方法没有声明所有可能发生的受查异常， 编译器就会发出一个错误消息。
     */

    public static void main(String[] args) throws FileFormatException {
        URL url = ClassLoader.getSystemResource("ExampleFile.txt");
        readTxt(new File(url.getPath()));
    }

    /**
     * TODO 抛出异常
     *  对于一个已经存在的异常类， 将其抛出非常容易
     *    1 ) 找到一个合适的异常类。
     *    2 ) 创建这个类的一个对象。
     *    3 ) 将对象抛出。
     *  在这种情况下:
     *  一旦方法抛出了异常， 这个方法就不可能返回到调用者。也就是说， 不必为返回的默认 值或错误代码担忧。
     */
    public static void readTxt(File file) throws FileFormatException {
        boolean b = file.getName().endsWith(".txt");
        if(!b) {
            throw new FileFormatException("文件错误，不是文本");
        }
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            int read;
            while ((read = is.read()) != -1){
                System.out.print((char) read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (Objects.nonNull(is)) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
