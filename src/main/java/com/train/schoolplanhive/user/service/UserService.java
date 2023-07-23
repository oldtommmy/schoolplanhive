package com.train.schoolplanhive.user.service;


import com.train.schoolplanhive.user.model.User;

/**
 * create by xiuzhong.li at 2022/5/9
 *
 * @Description:文件描述：用户服务接口，登录等功能
 */
public interface UserService {
    public User login(String username);

    public User getUser(String username);

    public User getUserById(Integer id);

    public String recoverPwd(String emailOrUsername);

    public void update(User user);
}