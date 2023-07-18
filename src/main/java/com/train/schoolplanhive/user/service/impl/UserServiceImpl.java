package com.train.schoolplanhive.user.service.impl;

import com.train.schoolplanhive.user.dao.UserDao;
import com.train.schoolplanhive.user.model.User;
import com.train.schoolplanhive.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * create by tommychan at 2023/7/18
 *
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User login(String username) {
        return userDao.getUser(username);
    }
}
