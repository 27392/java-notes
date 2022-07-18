package cn.haohaoli.lazy.service;

import cn.haohaoli.lazy.entity.User;

/**
 * @author lwh
 */
public class UserService {

    public User getById(Integer id) {
        System.out.println("获取用户");

        User user = new User();
        user.setId(id);
        return user;
    }
}
