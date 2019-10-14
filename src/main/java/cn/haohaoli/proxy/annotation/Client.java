package cn.haohaoli.proxy.annotation;

import java.lang.annotation.*;

/**
 * @author LiWenHao
 * @date 2019/9/28 10:14
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Client {

    String value() default "";
}
