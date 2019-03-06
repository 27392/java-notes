package cn.haohaoli.book.core.chapter6.interfaces.method;

import java.nio.file.FileSystems;

/**
 * TODO 接口静态方法
 *  在java8之前接口是不可以增加静态方法，在之前通常的做法都是将静态方法放在伴随类中。
 *  在标准库中， 你会看到成对出现 的接口和实用工具类， 如 Collection/Collections 或 Method/Paths。
 *  在java8中可以吧伴随类中的静态方法放在接口中，这样一来就不需要伴随类
 *  但是java库都之前都是以这种方法所以重构不太可能
 * @author LiWenHao
 * @date 2019-02-03 14:55
 */
public interface Static {

    /**
     * @see java.nio.file.Paths
     * 具体可以查看Paths类
     */
    static java.nio.file.Path get(String first, String... more) {
        return FileSystems.getDefault().getPath(first, more);
    }
}
