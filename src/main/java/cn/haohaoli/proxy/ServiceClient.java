package cn.haohaoli.proxy;

import cn.haohaoli.proxy.annotation.Client;
import cn.haohaoli.proxy.annotation.RequestMapping;

/**
 * @author LiWenHao
 * @date 2019/9/28 10:13
 */
@Client(value = "https://www.baidu.com")
public interface ServiceClient {

    /**
     * https://www.baidu.com//s?ie=utf-8&wd=123
     * @param keyword
     * @return
     */
    @RequestMapping("/s")
    String search (String keyword);
}
