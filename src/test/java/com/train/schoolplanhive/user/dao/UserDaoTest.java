package com.train.schoolplanhive.user.dao;


import com.train.schoolplanhive.user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * create by tommychan at 2023/7/18
 *
 * @Description:
 */
@SpringBootTest
public class UserDaoTest {
    @Autowired
    UserDao userDao;
    @Test
    void getUser() {
        User user = userDao.getUser("jerry");
        System.out.println(user);
        assertThat(user).isNotNull();
    }
}
