package cn.haohaoli.proxy.annotation;

import java.lang.annotation.*;

/**
 * @author LiWenHao
 * @date 2019/9/28 10:19
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    String value() default "";
}
