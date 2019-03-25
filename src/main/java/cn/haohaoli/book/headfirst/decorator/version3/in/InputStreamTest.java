package cn.haohaoli.book.headfirst.decorator.version3.in;

import java.io.*;
import java.net.URL;

/**
 * @author LiWenHao
 * @date 2019-03-25 21:18
 */
public class InputStreamTest {

    public static void main(String[] args) throws IOException {
        URL systemResource = ClassLoader.getSystemResource("ExampleFile.txt");
        InputStream in = new LowerCaseInputStream(new BufferedInputStream(new FileInputStream(systemResource.getPath())));
        int read;
        while ((read  = in.read()) != -1){
            System.out.print((char) read);
        }
    }
}
