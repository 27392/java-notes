package cn.haohaoli.book.core.chapter7.exception;

import java.io.*;

/**
 * TODO 捕获异常
 *   要想捕获一个异常，必须设置 try/catch 语句块
 *   如果在 try 语句块中的任何代码抛出了一个在 catch 子句中说明的异常类，那么
 *      1) 程序将跳过 try 语句块的其余代码。
 *      2) 程序将执行 catch 子句中的处理器代码。
 *   如果在 try 语句块中的代码没有拋出任何异常， 那么程序将跳过 catch 子句。
 *   如果方法中的任何代码拋出了一个在 catch 子句中没有声明的异常类型， 那么这个方法就会立刻退出
 * @author LiWenHao
 * @date 2019-02-07 16:28
 */
public class CatchException {

    public static void main(String[] args) {
        //捕获多个异常
        try {
            printFile(new File("/Users/liwenhao/Desktop/java-notes/README.md"));
        } catch (NullPointerException | IOException e){     //jdk 7 合并catch子句
            throw new RuntimeException("文件读取错误！", e.getCause());    //异常链
        }
    }

    /**
     * TODO 处理异常
     *  应该捕获那些知道如何处理的异常， 而将那些不知道怎样处理的异常继续进行传递。
     *  如果想传递一个异常， 就必须在方法的首部添加一个 throws 说明符， 以便告知调用者这个方法可能会抛出异常。
     *   编译器严格地执行 throws 说明符。 如果调用了一个抛出受查异常的方法， 就必须对它进行处理， 或者继续传递。
     */
    private static void printFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while (null != (line = br.readLine())){
            System.out.println(line);
        }
    }
}
