package cn.haohaoli.book.core.chapter7.exception;

import java.io.*;
import java.util.Objects;

/**
 * TODO finally
 *  不管是否又异常被捕获，finally子句中的代码都被执行
 * @author LiWenHao
 * @date 2019-02-08 16:19
 */
public class FinallyTest {

    public static void main(String[] args) {
        System.out.println(finallyReturnExample());
        try {
            readFileThrow(new File("/Users/liwenhao/Desktop/java-notes/README.md"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        readFile(new File("/Users/liwenhao/Desktop/java-notes/README.md"));
    }

    /**
     * TODO try语句可以只有finally子句， 而没有catch子句。
     *  无论在try语句块中是否遇到异常，finally子句中的 in.close() 语句都会被执行。
     *  当然,如果真的遇到一个异常，这个异常将会被重新抛出， 并且必须由另一个catch子句捕获
     */
    private static void readFileThrow (File file) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            print(br);
        } finally{
            try {
                if (Objects.nonNull(br)) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *  TODO 强烈建议解搞合 try/catch 和 try/finally 语句块。 这样可以提高代码的清晰度
     *   内层的 try 语句块只有一个职责， 就是确保关闭输入流。
     *   外层的 try 语句块也只有一个职责， 就是确保报告出现的错误。
     *   这种设计方式不仅清楚， 而且还具有一个功能， 就是将会报告 finally 子句中出现的错误。
     */
    private static void readFile (File file) {
        BufferedReader br = null;
        try {
            try {
                br = new BufferedReader(new FileReader(file));
                print(br);
            } finally {
                if (Objects.nonNull(br)) {
                    br.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * TODO finally子句中包含return语句
     *  假设利用 return 语句从try语句块中退出。在方法返回前，finally子句的内容将被执行。
     *  如果 finally子句中 也有一个 return 语句， 这个返回值将会覆盖原始的返回值
     */
    private static int finallyReturnExample (){
        try{
            int i = 1/1;
            return i;
        } finally {
            System.out.println("finally！");
            return -1;
        }
    }

    /**
     * 打印
     * @param br            缓冲流
     * @throws IOException
     */
    private static void print (BufferedReader br) throws IOException {
        String s;
        while (null != (s = br.readLine())) {
            System.out.println(s);
        }
    }
}
