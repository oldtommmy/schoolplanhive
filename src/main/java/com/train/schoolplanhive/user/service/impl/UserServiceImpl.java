package com.train.schoolplanhive.user.service.impl;


import com.train.schoolplanhive.user.dao.UserDao;
import com.train.schoolplanhive.user.model.User;
import com.train.schoolplanhive.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * create by xiuzhong.li at 2022/5/9
 *
 * @Description:文件描述：
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User login(String username) {
        return userDao.getUser(username);
    }

    @Override
    public String recoverPwd(String emailOrUsername) {
        User userByName = userDao.getUser(emailOrUsername);
        User userByEmail = userDao.getUserByEmail(emailOrUsername);
        if (userByEmail == null && userByName == null) {
            return "Error";
        }
        if (userByName != null) {
            userByName.setPwd("111111");
            userDao.updateUser(userByName);
        } else {
            userByEmail.setPwd("111111");
            userDao.updateUser(userByEmail);
        }
        return "Success";
    }

    @Override
    public void update(User user) {
        userDao.updateUser(user);
    }
}