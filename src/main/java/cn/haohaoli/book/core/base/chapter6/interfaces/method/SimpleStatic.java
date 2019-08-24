package cn.haohaoli.book.core.base.chapter6.interfaces.method;

import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * @author LiWenHao
 * @date 2019/8/24 9:54
 */
public interface SimpleStatic extends Static {

    /**
     * TODO 接口继承并不会继承static方法
     */
    // 与Static接口中get() 方法一致 并不会有冲突! 说明接口继承并不会继承static方法
    static Path get(String first, String... more) {
        return FileSystems.getDefault().getPath(first, more);
    }
}
