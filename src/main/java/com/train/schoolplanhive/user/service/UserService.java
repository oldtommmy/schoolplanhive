package com.train.schoolplanhive.user.service;


import com.train.schoolplanhive.user.model.User;


public interface UserService {
    public User login(String username);

    public User getUser(String username);

    public User getUserById(Integer id);

    public String recoverPwd(String emailOrUsername);

    public void updatePwd(User user);

    public void update(User user);

    public void add(User user);
}