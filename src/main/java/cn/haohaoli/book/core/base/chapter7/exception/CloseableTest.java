package cn.haohaoli.book.core.base.chapter7.exception;

import java.io.*;
import java.net.URL;

/**
 * @author LiWenHao
 * @date 2019/10/9 13:53
 */
public class CloseableTest {

    public static void main(String[] args) {

        URL resource = CloseableTest.class.getClassLoader().getResource("ExampleFile.txt");

        File file = new File("xxx.txt");

        /**
         * TODO OutputStream,OutputStream 都实现了Closeable接口,而Closeable接口继承了AutoCloseable接口
         */
        try (InputStream inputStream = resource.openConnection().getInputStream();
             OutputStream outputStream = new FileOutputStream(file)) {
            byte[] buff = new byte[1024];
            while (inputStream.read(buff, 0, buff.length) != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            boolean delete = file.delete();
            System.out.println("删除文件" + (delete ? "成功" : "失败"));
        }

        // TODO 实现了AutoCloseable接口的类使用try-whit-resource时会自动调用close方法
        try (Close close = new Close()) {
            System.out.println(close.getClass().getSimpleName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Close
        // 执行了close
    }

    static class Close implements AutoCloseable {

        @Override
        public void close() {
            System.out.println("执行了close");
        }
    }
}
