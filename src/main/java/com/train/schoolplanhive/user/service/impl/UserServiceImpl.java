package com.train.schoolplanhive.user.service.impl;


import com.train.schoolplanhive.user.dao.UserDao;
import com.train.schoolplanhive.user.model.User;
import com.train.schoolplanhive.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User login(String username) {
        return userDao.getUser(username);
    }

    @Override
    public User getUser(String username) {
        return userDao.getUser(username);
    }

    @Override
    public User getUserById(Integer id) {
        return userDao.getUserById(id);
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
    public void updatePwd(User user) {
        userDao.updatePwd(user);
    }

    @Override
    public void update(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void add(User user) {
        userDao.insert(user);
    }
}