package com.train.schoolplanhive.user.dao;
import com.train.schoolplanhive.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * create by tommychan at 2023/7/18
 *
 * @Description: 用户操作持久层
 */

@Mapper
@Repository
public interface UserDao {
    @Select("select * from userinfo where username=#{username}")
    public User getUser(String username);
}
